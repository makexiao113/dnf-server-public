package com.aiyi.game.dnfserver.service.dtaiwan.impl;

import com.aiyi.game.dnfserver.entity.dtaiwan.Account;
import com.aiyi.game.dnfserver.mapper.dtaiwan.AccountMapper;
import com.aiyi.game.dnfserver.service.dtaiwan.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * impl账户服务
 *
 * @author 马珂骁
 * @date 2022/09/01
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}