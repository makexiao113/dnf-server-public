package com.aiyi.game.dnfserver.advice;

import com.aiyi.game.dnfserver.bean.result.Result;
import com.aiyi.game.dnfserver.constant.ExceptionConstants;
import com.aiyi.game.dnfserver.exception.AccessOauthException;
import com.aiyi.game.dnfserver.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 统一异常处理类
 *
 * @author makexiao
 * @date 2022-09-01
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> errorHandler(Exception ex) {
        logger.error("系统异常!{}", ex.getMessage());
        return Result.error(ExceptionConstants.SYSTEM_EXCEPTION);
    }

    /**
     * 访问oauth异常
     * 登录权限认证异常401
     *
     * @param e e
     * @return {@link Result}<{@link Object}>
     */
    @ExceptionHandler(value = AccessOauthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<Object> accessOauthException(Exception e) {
        logger.error("没有权限!{}", e.getMessage());
        return Result.error(HttpStatus.UNAUTHORIZED.value(), ExceptionConstants.UNAUTHORIZED);
    }

    /**
     * 非法参数异常
     *
     * @param e e
     * @return {@link Result}
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result<Object> illegalArgumentException(Exception e) {
        logger.error("非法参数异常!{}", e.getMessage());
        return Result.error(HttpStatus.BAD_REQUEST.value(), ExceptionConstants.BAD_REQUEST);
    }

    /**
     * 非法参数异常
     *
     * @param e e
     * @return {@link Result}
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result<Object> serviceException(Exception e) {
        logger.error("服务异常!{}", e.getMessage());
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstants.INTERNAL_SERVER_ERROR);
    }


}