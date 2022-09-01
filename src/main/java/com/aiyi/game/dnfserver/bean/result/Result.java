package com.aiyi.game.dnfserver.bean.result;

import com.aiyi.game.dnfserver.constant.Constants;

import java.io.Serializable;

/**
 * 返回结果集
 *
 * @author makexiao
 * @date 2022/09/01
 */
public class Result<T> implements Serializable {
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 返回编码
     */
    private Integer code;

    private Result() {
    }

    public Result(String msg, T data, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static <T> Result<T> ok() {
        return restResult(null, Constants.SUCCESS, "操作成功");
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, Constants.SUCCESS, "操作成功");
    }

    public static <T> Result<T> ok(String msg, T data) {
        return restResult(data, Constants.SUCCESS, msg);
    }

    public static <T> Result<T> error() {
        return restResult(null, Constants.FAIL, (String) null);
    }

    public static <T> Result<T> error(String msg) {
        return restResult(null, Constants.FAIL, msg);
    }

    public static <T> Result<T> error(T data) {
        return restResult(data, Constants.FAIL, (String) null);
    }

    public static <T> Result<T> error(T data, String msg) {
        return restResult(data, Constants.FAIL, msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}