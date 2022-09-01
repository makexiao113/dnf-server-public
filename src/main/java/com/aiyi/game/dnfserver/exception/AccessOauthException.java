package com.aiyi.game.dnfserver.exception;

import com.aiyi.game.dnfserver.constant.ExceptionConstants;

/**
 * 访问oauth异常
 *
 * @author makexiao
 * @date 2022/09/01
 */
public class AccessOauthException extends Exception {

    public AccessOauthException() {
        super(ExceptionConstants.ACCESS_EXCEPTION);
    }

    public AccessOauthException(String msg) {
        super(msg);
    }

    public AccessOauthException(String msg, Throwable e) {
        super(msg, e);
    }

    public AccessOauthException(Throwable e, String msg) {
        super(msg, e);
    }
}