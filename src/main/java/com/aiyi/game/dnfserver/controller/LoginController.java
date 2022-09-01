package com.aiyi.game.dnfserver.controller;

import com.aiyi.core.exception.ValidationException;
import com.aiyi.game.dnfserver.conf.NoLogin;
import com.aiyi.game.dnfserver.dao.User;
import com.aiyi.game.dnfserver.handler.rmt.RmtService;
import com.aiyi.game.dnfserver.utils.Common;
import com.aiyi.game.dnfserver.utils.cache.UserTokenCacheUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Copyright (c) 2021 HEBEI CLOUD IOT FACTORY BIGDATA CO.,LTD.
 * Legal liability shall be investigated for unauthorized use
 *
 * @Author: Guo Shengkai
 * @Date: Create in 2021/04/15 9:18
 */
@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link String}
     */
    @PostMapping
    @NoLogin
    public String login(@RequestBody User user){
        user.setClientId(rmtService.getClientId());
        if (!(user.getAccount().equals(rmtService.getAccount()) && user.getPassword().equals(rmtService.getPassword()))){
            throw new ValidationException("账号或密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        UserTokenCacheUtil.putUserCache(token, user);
        return token;
    }
}
