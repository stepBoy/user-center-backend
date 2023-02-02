package com.ycu.userCenter.exception;


import com.ycu.userCenter.common.BusinessStatus;
import com.ycu.userCenter.common.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(BusinessException.class)
    public ResResult<?> businessExceptionHandler(BusinessException e){

        log.error("errorMessage: {}",e.getMessage());


        return ResResult.error(e.getCode(),e.getMessage(),e.getDesc());


    }


    @ExceptionHandler(Exception.class)
    public ResResult<?> systemExceptionHandler(Exception e){

        log.error("errorMessage: {}",e.getMessage());

        return ResResult.error(BusinessStatus.SYSTEM_ERROR.getCode()
                ,BusinessStatus.SYSTEM_ERROR.getMessage());


    }





}
