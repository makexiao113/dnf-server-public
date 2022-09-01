package com.aiyi.game.dnfserver.controller;

import com.aiyi.game.dnfserver.bean.login.AccountVO;
import com.aiyi.game.dnfserver.bean.result.Result;
import com.aiyi.game.dnfserver.conf.NoLogin;
import com.aiyi.game.dnfserver.handler.AccountHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Copyright (c) 2021 HEBEI CLOUD IOT FACTORY BIGDATA CO.,LTD.
 * Legal liability shall be investigated for unauthorized use
 *
 * @Author: Guo Shengkai
 * @Date: Create in 2021/04/14 15:10
 */
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    /**
     * 账户处理程序
     */
    private final AccountHandler accountHandler;

    public AccountController(
            AccountHandler accountHandler
    ) {
        this.accountHandler = accountHandler;
    }

    /**
     * 查询账号列表
     *
     * @param account       账号
     * @param loginStatus   是否登录
     * @param lastLoginDate 最后登录时间
     * @param page          页码
     * @param pageSize      每页条数
     * @return AccountVO
     */
    @GetMapping
    public Result<AccountVO> list(String account, Boolean loginStatus,
                                  Date lastLoginDate, Integer page,
                                  Integer pageSize) {
        if (null == page) {
            page = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        return accountHandler.list(account, loginStatus, lastLoginDate,
                page, pageSize);
    }

    /**
     * 账号注册
     *
     * @param accountVO 账号信息
     * @return Result<Object>
     */
    @PostMapping
    @NoLogin
    public Result<Object> register(@RequestBody AccountVO accountVO) {
        accountHandler.register(accountVO);
        return Result.ok();
    }

    /**
     * 获得用户详情
     *
     * @param uid 用户ID
     * @return AccountVO
     */
    @GetMapping("{uid}")
    public AccountVO info(@PathVariable int uid) {
        return accountHandler.info(uid);
    }

}
