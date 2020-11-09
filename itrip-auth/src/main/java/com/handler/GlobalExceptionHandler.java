package com.handler;

import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.RetrunResult;
import com.hzl.exception.DaoException;
import com.hzl.exception.ServiceException;
import com.hzl.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveHyperLogLogCommands;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-09 14:37
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获异常 Exception,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = Exception.class)
    public RetrunResult error(Exception e) {
        log.error(e.getMessage(), e);
        return RetrunResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获异常 Exception,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = Exception.class)
    public RetrunResult error(DaoException e) {
        log.error(e.getMessage(), e);
        return RetrunResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获并处理service异常,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = Exception.class)
    public RetrunResult error(ServiceException e) {
        log.error(e.getMessage(), e);
        return RetrunResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获并处理自定义系统异常,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = Exception.class)
    public RetrunResult error(SysException e) {
        log.error(e.getMessage(), e);
        return RetrunResult.error(ErrorCodeEnum.FAILED);
    }



}
