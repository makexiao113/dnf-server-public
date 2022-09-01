package com.aiyi.game.dnfserver.advice;

import com.aiyi.game.dnfserver.bean.result.Result;
import com.aiyi.game.dnfserver.constant.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 *
 * @author makexiao
 * @date 2022/09/01
 */
@ControllerAdvice
public class BaseExceptionControllerAdvice {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionControllerAdvice.class);


}
