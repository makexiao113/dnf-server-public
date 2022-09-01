package com.aiyi.game.dnfserver.controller;

import com.aiyi.game.dnfserver.bean.login.AccountVO;
import com.aiyi.game.dnfserver.conf.NoLogin;
import com.aiyi.game.dnfserver.handler.AccountHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 客户端账号操作Web接口
 *
 * @author gsk
 */
@RestController
public class ClientAccountController {

    @Resource
    private AccountHandler accountHandler;

    /**
     * 客户端用户登录接口
     *
     * @param accountVO 要登录的用户
     * @return String
     * 该用户的客户端授权Key
     */
    @PostMapping("client/login")
    @NoLogin
    public String loginClient(AccountVO accountVO) {
        return accountHandler.loginClient(accountVO);
    }

    @PostMapping("api/v1/client/login")
    @NoLogin
    public String loginClientApi(@RequestBody AccountVO accountVO) {
        return accountHandler.loginClient(accountVO);
    }

    /**
     * 注册
     *
     * @param accountVO 账户签证官
     * @return {@link String}
     */
    @GetMapping("client/register")
    @NoLogin
    public String register(AccountVO accountVO) {
        accountHandler.register(accountVO);
        return "注册成功";
    }
}
