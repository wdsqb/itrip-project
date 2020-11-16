package com.hzl.itripauth.handler;

import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.ReturnResult;
import com.hzl.exception.DaoException;
import com.hzl.exception.ServiceException;
import com.hzl.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :  捕获异常类
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
    public ReturnResult error(Exception e) {
        log.error(e.getMessage(), e);
        return ReturnResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获异常 Exception,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = DaoException.class)
    public ReturnResult error(DaoException e) {
        log.error(e.getMessage(), e);
        return ReturnResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获并处理service异常,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = ServiceException.class)
    public ReturnResult error(ServiceException e) {
        log.error(e.getMessage(), e);
        return ReturnResult.error(ErrorCodeEnum.FAILED);
    }

    /**
     * 捕获并处理自定义系统异常,主要用于解决漏网之鱼
     * @return Exception
     */
    @ExceptionHandler(value = SysException.class)
    public ReturnResult error(SysException e) {
        log.error(e.getMessage(), e);
        return ReturnResult.error(ErrorCodeEnum.FAILED);
    }



}
