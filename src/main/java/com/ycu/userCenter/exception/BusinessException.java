package com.ycu.userCenter.exception;

import com.ycu.userCenter.common.BusinessStatus;
import lombok.Data;


public class BusinessException extends RuntimeException {

    private final int code;

    private final String desc;



    public BusinessException(BusinessStatus businessStatus,String desc) {
        super(businessStatus.getMessage());
        this.code = businessStatus.getCode();

        this.desc = desc;

    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
