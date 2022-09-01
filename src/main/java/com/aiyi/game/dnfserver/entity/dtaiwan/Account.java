package com.aiyi.game.dnfserver.entity.dtaiwan;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 账户
 * DNF用户实体
 *
 * @author makexiao
 * @date 2022/09/01
 */
@TableName("d_taiwan.`accounts`")
public class Account implements Serializable {
    /**
     * 账号ID
     */
    @TableId
    @TableField("UID")
    private Integer uid;
    /**
     * 账号
     */
    @TableField("accountname")
    private String accountName;
    /**
     * 密码
     */
    private String password;
    /**
     * QQ号
     */
    private String qq;
    /**
     * VIP
     */
    private String vip;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
