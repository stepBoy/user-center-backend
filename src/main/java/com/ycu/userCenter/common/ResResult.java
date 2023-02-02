package com.ycu.userCenter.common;


import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;

@Data
public class ResResult<T> implements Serializable {

    private static final long serialVersionUID = -8864200070629650461L;
    private T data;

    private String message;

    private String desc;

    private int code;

    public static <R> ResResult<R> success(R data){

        ResResult<R> rResResult = new ResResult<>();

        rResResult.setData(data);

        rResResult.setCode(BusinessStatus.SUCCESS.getCode());

        rResResult.setMessage(BusinessStatus.SUCCESS.getMessage());

        return rResResult;


    }

    public static <R> ResResult<R> success(){

        ResResult<R> rResResult = new ResResult<>();

        rResResult.setData(null);

        rResResult.setCode(BusinessStatus.SUCCESS.getCode());

        rResResult.setMessage(BusinessStatus.SUCCESS.getMessage());

        return rResResult;


    }

    public static ResResult<?> error(int code,String message,String desc){

        ResResult<?> rResResult = new ResResult<>();

        rResResult.setData(null);

        rResResult.setCode(code);

        rResResult.setMessage(message);

        rResResult.setDesc(desc);

        return rResResult;

    }

    public static  ResResult<?> error(int code,String message){

        ResResult<?> rResResult = new ResResult<>();

        rResResult.setData(null);

        rResResult.setCode(code);

        rResResult.setMessage(message);



        return rResResult;

    }







}
