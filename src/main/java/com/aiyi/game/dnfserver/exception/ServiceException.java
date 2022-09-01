package com.aiyi.game.dnfserver.exception;

/**
 * 服务异常
 *
 * @author 马珂骁
 * @date 2022/09/01
 */
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 详细信息
     */
    private String detailMessage;

    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}