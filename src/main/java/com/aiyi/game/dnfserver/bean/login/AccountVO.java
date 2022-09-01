package com.aiyi.game.dnfserver.bean.login;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户签证官
 * DNF用户实体
 *
 * @author makexiao
 * @date 2022/09/01
 */
public class AccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号ID
     */
    private Integer uid;
    /**
     * 账号
     */
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
    private String ip;

    /**
     * 有效代码
     */
    private String validCode;

    /**
     * 短信代码
     */
    private String smsCode;

    /**
     * 登录频道号
     */
    private Integer channelNo;

    /**
     * 登录状态
     */
    private Boolean loginStatus;

    /**
     * 最后登录时间
     */
    private Date lastLoginDate;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 塞拉
     */
    private Integer cera;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
    }

    public Boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getCera() {
        return cera;
    }

    public void setCera(Integer cera) {
        this.cera = cera;
    }

    @Override
    public String toString() {
        return "AccountVO{" +
                "uid=" + uid +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", ip='" + ip + '\'' +
                ", validCode='" + validCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", channelNo=" + channelNo +
                ", loginStatus=" + loginStatus +
                ", lastLoginDate=" + lastLoginDate +
                ", loginIp='" + loginIp + '\'' +
                ", cera=" + cera +
                '}';
    }
}
