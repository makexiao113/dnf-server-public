package com.aiyi.game.dnfserver.entity.dtaiwan;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 成员信息
 *
 * @author 马珂骁
 * @date 2022/09/01
 */
@TableName("d_taiwan.member_info")
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 表主键
     */
    @TableId
    private Integer mId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     *
     */
    private String firstSsn;
    /**
     *
     */
    private String secondSsn;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 电话号
     */
    private String mobileNo;
    /**
     * 注册时间
     */
    private Long regDate;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 问题编号
     */
    private Integer qNo;
    /**
     * 问题回答
     */
    private String qAnswer;
    /**
     * 更新时间
     */
    private Date updtDate;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 是否使用邮箱 是y，否n
     */
    private String emailYn;
    /**
     * 是否检查ssn
     */
    private Integer ssnCheck;
    /**
     *是否有空档
     */
    private Integer slot;
    /**
     * 上次游玩时间
     */
    private Date lastPlayTime;
    /**
     *
     */
    private Integer hangameFlag;
    /**
     *
     */
    private Integer hanmonFlag;
    /**
     * 用户类型
     */
    private Integer mType;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstSsn() {
        return firstSsn;
    }

    public void setFirstSsn(String firstSsn) {
        this.firstSsn = firstSsn;
    }

    public String getSecondSsn() {
        return secondSsn;
    }

    public void setSecondSsn(String secondSsn) {
        this.secondSsn = secondSsn;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getRegDate() {
        return regDate;
    }

    public void setRegDate(Long regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getqNo() {
        return qNo;
    }

    public void setqNo(Integer qNo) {
        this.qNo = qNo;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public Date getUpdtDate() {
        return updtDate;
    }

    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmailYn() {
        return emailYn;
    }

    public void setEmailYn(String emailYn) {
        this.emailYn = emailYn;
    }

    public Integer getSsnCheck() {
        return ssnCheck;
    }

    public void setSsnCheck(Integer ssnCheck) {
        this.ssnCheck = ssnCheck;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Date getLastPlayTime() {
        return lastPlayTime;
    }

    public void setLastPlayTime(Date lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public Integer getHangameFlag() {
        return hangameFlag;
    }

    public void setHangameFlag(Integer hangameFlag) {
        this.hangameFlag = hangameFlag;
    }

    public Integer getHanmonFlag() {
        return hanmonFlag;
    }

    public void setHanmonFlag(Integer hanmonFlag) {
        this.hanmonFlag = hanmonFlag;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }
}