package com.aiyi.game.dnfserver.handler.impl;

import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.exception.ValidationException;
import com.aiyi.game.dnfserver.bean.result.Result;
import com.aiyi.game.dnfserver.conf.CommonAttr;
import com.aiyi.game.dnfserver.constant.ExceptionConstants;
import com.aiyi.game.dnfserver.dao.AccountDao;
import com.aiyi.game.dnfserver.bean.login.AccountVO;
import com.aiyi.game.dnfserver.entity.dtaiwan.Account;
import com.aiyi.game.dnfserver.entity.dtaiwan.MemberInfo;
import com.aiyi.game.dnfserver.entity.dtaiwan.MemberWhiteAccount;
import com.aiyi.game.dnfserver.entity.taiwanbilling.CashCera;
import com.aiyi.game.dnfserver.entity.taiwanbilling.CashCeraPoint;
import com.aiyi.game.dnfserver.entity.taiwancain2nd.MemberAvatarCoin;
import com.aiyi.game.dnfserver.entity.taiwanlogin.MemberLogin;
import com.aiyi.game.dnfserver.exception.GlobalException;
import com.aiyi.game.dnfserver.exception.ServiceException;
import com.aiyi.game.dnfserver.handler.AccountHandler;
import com.aiyi.game.dnfserver.service.dtaiwan.AccountService;
import com.aiyi.game.dnfserver.service.dtaiwan.MemberInfoService;
import com.aiyi.game.dnfserver.service.dtaiwan.MemberWhiteAccountService;
import com.aiyi.game.dnfserver.service.taiwanbilling.CashCeraPointService;
import com.aiyi.game.dnfserver.service.taiwanbilling.CashCeraService;
import com.aiyi.game.dnfserver.service.taiwancain2nd.MemberAvatarCoinService;
import com.aiyi.game.dnfserver.service.taiwanlogin.MemberLoginService;
import com.aiyi.game.dnfserver.utils.MD5;
import com.aiyi.game.dnfserver.utils.MinFieldUtil;
import com.aiyi.game.dnfserver.utils.RSATool;
import com.aiyi.game.dnfserver.utils.String2Hex;
import com.aiyi.game.dnfserver.utils.cache.CacheUtil;
import com.aiyi.game.dnfserver.utils.cache.Key;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户业务操作类
 */
@Service
public class AccountHandlerImpl implements AccountHandler {

    @Resource
    private AccountDao accountDao;
//
//    @Resource
//    private AccountVODao accountVODao;

    private final AccountService accountService;

    /**
     * 现金塞拉点服务
     */
    private final CashCeraPointService cashCeraPointService;

    /**
     * 现金塞拉服务
     */
    private final CashCeraService cashCeraService;

    /**
     * 会员信息服务
     */
    private final MemberInfoService memberInfoService;

    /**
     * 成员阿凡达硬币服务
     */
    private final MemberAvatarCoinService memberAvatarCoinService;

    /**
     * 白色成员账户服务
     */
    private final MemberWhiteAccountService memberWhiteAccountService;

    /**
     * 会员登录服务
     */
    private final MemberLoginService memberLoginService;

    public AccountHandlerImpl(
            AccountService accountService,
            CashCeraPointService cashCeraPointService,
            CashCeraService cashCeraService,
            MemberInfoService memberInfoService,
            MemberAvatarCoinService memberAvatarCoinService,
            MemberWhiteAccountService memberWhiteAccountService,
            MemberLoginService memberLoginService
    ) {
        this.accountService = accountService;
        this.cashCeraPointService = cashCeraPointService;
        this.cashCeraService = cashCeraService;
        this.memberAvatarCoinService = memberAvatarCoinService;
        this.memberInfoService = memberInfoService;
        this.memberWhiteAccountService = memberWhiteAccountService;
        this.memberLoginService = memberLoginService;
    }

    @Override
    public String loginClient(AccountVO accountVO) {
        // 账号密码匹配用户
        String password = MD5.getMd5(accountVO.getPassword());
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountname", accountVO.getAccountName());
        queryWrapper.eq("password", password);
        List<Account> accounts = accountService.list(queryWrapper);
        if (CollectionUtils.isEmpty(accounts) || accounts.size() > 1) {
            throw new GlobalException(ExceptionConstants.BAD_PASSWORD);
        }
        Account account = accounts.get(0);
        // 得到待加密的用户标识
        String
                data = String.format("%08x0101010101010101010101010101010101010101010101010101010101010101559145100" +
                "10403030101", account.getUid());
        // 加密计算出用户授权Key
        String token = null;
        try {
            String privateKey = new String(MinFieldUtil.readResource("private.key")).replace("\r", "")
                    .replace("\n", "");
            byte[] resultByte = RSATool.encryptByPrivateKey(data.getBytes(), privateKey);
            token = Base64.getEncoder().encodeToString(resultByte);
        } catch (Exception e) {
            byte[] resultByte = RSATool.encryptByPrivateKey(data.getBytes(), ("-----BEGIN RSA PRIVATE KEY-----\n" +
                    new String(MinFieldUtil.readResource("private.key")) + "\n-----END RSA PRIVATE KEY-----").getBytes());
            token = Base64.getEncoder().encodeToString(resultByte);
        }
        return token;
    }

    @Override
    public void register(AccountVO accountVO) {
        if (!StringUtils.hasLength(accountVO.getAccountName().trim())) {
            throw new ServiceException("用户名不能为空");
        }
        if (!StringUtils.hasLength(accountVO.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        // 从缓存中获取验证码，
        Integer sms = CacheUtil.get(Key.as(CommonAttr.CACHE.VALIDATION_CODE, "SMS", "Register", accountVO.getAccountName()), Integer.class);
        if (null == sms || !sms.toString().equals(accountVO.getSmsCode())) {
            throw new ServiceException("验证码不正确或已过期");
        }
        // 检查是否已存在此用户名
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountname", accountVO.getAccountName());
        long accountNum = accountService.count(queryWrapper);
        if (accountNum > 0L) {
            throw new ServiceException("该用户名已存在, 请更换其他用户名");
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountVO, account);
        account.setPassword(MD5.getMd5(accountVO.getPassword()));
        // 保存用户信息
        accountService.save(account);
        // 初始化用户信息
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setmId(account.getUid());
        memberInfo.setUserId(account.getUid().toString());
        memberInfoService.save(memberInfo);
        // 将用户信息保存到白名单
        MemberWhiteAccount memberWhiteAccount = new MemberWhiteAccount();

        memberWhiteAccountService.save(memberWhiteAccount);
        // 初始化用户统计信息
        MemberLogin memberLogin = new MemberLogin();
        memberLoginService.save(memberLogin);
        // 初始化用户商城点券信息
        CashCera cashCera = new CashCera();
        cashCeraService.save(cashCera);
        // 初始化用户商城代金券信息
        CashCeraPoint cashCeraPoint = new CashCeraPoint();
        cashCeraPointService.save(cashCeraPoint);
        // 初始化用户头像币信息，目前不知道这个是干嘛的
        MemberAvatarCoin memberAvatarCoin = new MemberAvatarCoin();
        memberAvatarCoinService.save(memberAvatarCoin);
//        accountDao.execute("INSERT INTO d_taiwan.member_info(m_id, user_id)VALUES('" + accountVO.getUid() + "', '" + accountVO.getUid() + "')");
//        accountDao.execute("INSERT INTO d_taiwan.member_white_account(m_id)VALUES('" + accountVO.getUid() + "')");
//        accountDao.execute("INSERT INTO taiwan_login.member_login(m_id)VALUES('" + accountVO.getUid() + "')");
//        accountDao.execute("INSERT INTO taiwan_billing.cash_cera(account, cera,mod_tran, mod_date, reg_date)VALUES('" + accountVO.getUid() + "', 1000, 0, NOW(), NOW())");
//        accountDao.execute("INSERT INTO taiwan_billing.cash_cera_point(account, cera_point,mod_date, reg_date)VALUES('" + accountVO.getUid() + "', 0, NOW(), NOW())");
//        accountDao.execute("INSERT INTO taiwan_cain_2nd.member_avatar_coin(m_id)VALUES('" + accountVO.getUid() + "')");
        /*
         *# 充值10点券
         * update taiwan_billing.cash_cera set cera = cera + 10 where account='1';
         *
         * # 充值10代币券
         * update taiwan_billing.cash_cera_point SET cera_point = cera_point + 10 where account='1';
         *
         * # 解封
         * delete from d_taiwan.member_punish_info where m_id='2'
         *
         * # 禁封1天
         * insert into d_taiwan.member_punish_info (m_id,punish_type,occ_time,punish_value,apply_flag,start_time,end_time,reason) values ('uid','1',NOW(),'101','2',NOW(), date_sub(
         * 	NOW(),interval -1 day
         * ),'GM')
         *
         * # 发送邮件
         * insert into taiwan_cain_2nd.postal (occ_time,send_charac_name,receive_charac_no,amplify_option,amplify_value,seperate_upgrade,seal_flag,item_id,add_info,upgrade,gold,letter_id) values (” ＋ “'” ＋ time ＋ “'” ＋ “,'DNF admin','” ＋ 角色列表.取标题 (角色列表.现行选中项, 0) ＋ “','” ＋ 红字 ＋ “','” ＋ 编辑框8.内容（默认0） ＋ “','” ＋ 锻造等级 ＋ “','” ＋ 封装 ＋ “','” ＋ 物品代码 ＋ “','” ＋ 物品数量 ＋ “','” ＋强化等级 ＋ “','” ＋ 游戏金币 ＋ “','” ＋ 信件ID，对应letter表，但这个表是空的 ＋ “')
         *
         */
    }

    @Override
    public Result<AccountVO> list(String account, Boolean loginStatus,
                                  Date lastLoginDate, int page,
                                  int pageSize) {

        if (!StringUtils.isEmpty(account)) {
            // 如果account不为空则先查询Account表后查询login表
            Page<Account> accountPage = new Page<>();
            accountPage.setCurrent(page);
            accountPage.setSize(pageSize);
            QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
            queryWrapper.likeRight(!StringUtils.isEmpty(account), "account", account);
            Page<Account> accountIPage = accountService.page(accountPage, queryWrapper);
            List<Integer> uids = accountIPage.getRecords().stream().map(Account::getUid).collect(Collectors.toList());
            // 查询login记录
            QueryWrapper<>

        } else {
            // 如果account为空则先查询login表后查询Account表
        }
        List<AccountVO> list = accountDao.list(account, loginStatus,
                lastLoginDate, (page - 1) * pageSize, pageSize);
        int count = accountDao.count(account, loginStatus, lastLoginDate,
                page, pageSize);
        return new Result.ok();

    }

    @Override
    public void recharge(int uid, int face) {
        AccountVO accountVO = accountVODao.get(uid);
        if (null == accountVO) {
            throw new ValidationException("Undefined the user!");
        }
        accountVODao.execute("update taiwan_billing.cash_cera set " +
                "cera = cera + ? where account=?", face, uid);

    }

    @Override
    public AccountVO info(int uid) {
        AccountVO accountVO = accountVODao.get(uid);
        List<AccountVO> list = accountVODao.list("SELECT cera FROM taiwan_billing.cash_cera " +
                "WHERE account=?", uid);
        accountVO.setCera(list.get(0).getCera());
        return accountVO;
    }

    public static void main(String[] args) {
        String data = String.format("%08x0101010101010101010101010101010101010101010101010101010101010101559145100" +
                "10403030101", 1);
        data = String2Hex.convertHexToString(data);
        // 加密计算出用户授权Key

        String token = null;
        try {
            String privateKey = new String(MinFieldUtil.readResource("private.key")).replace("\r", "")
                    .replace("\n", "");
            byte[] resultByte = RSATool.encryptByPrivateKey(data.getBytes(), privateKey);
            token = Base64.getEncoder().encodeToString(resultByte);
        } catch (Exception e) {
            byte[] resultByte = RSATool.encryptByPrivateKey(data.getBytes(), ("-----BEGIN RSA PRIVATE KEY-----\n" +
                    new String(MinFieldUtil.readResource("private.key")) + "\n-----END RSA PRIVATE KEY-----").getBytes());
            token = Base64.getEncoder().encodeToString(resultByte);
        }

        System.out.println(token);
    }
}
