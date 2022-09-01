package com.aiyi.game.dnfserver.controller;

import com.aiyi.game.dnfserver.bean.login.AccountVO;
import com.aiyi.game.dnfserver.handler.AccountHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * chong zhi
 */
@RestController
@RequestMapping("api/v1/recharge")
public class RechargeController {

    @Resource
    private AccountHandler accountService;

    /**
     * recharge in the user
     * @param accountVO
     *      the recharge info
     */
    @PostMapping
    public void recharge(@RequestBody AccountVO accountVO){
        accountService.recharge(accountVO.getUid(), accountVO.getCera());
    }


}
