package com.aiyi.game.dnfserver.exception;

import java.io.Serializable;

/**
 * 全局异常
 *
 * @author 马珂骁
 * @date 2022/09/01
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 消息
     */
    private String message;
    /**
     * 详细信息
     */
    private String detailMessage;

    public GlobalException() {
    }

    public GlobalException(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public GlobalException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public GlobalException setMessage(String message) {
        this.message = message;
        return this;
    }
}
