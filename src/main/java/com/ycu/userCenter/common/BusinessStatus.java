package com.ycu.userCenter.common;

/**
 * 业务错误的枚举类
 */

public enum BusinessStatus {
    PARAMS_ERROR(4000,"请求参数错误"),

    LOGIN_ERROR(4001,"登录失败"),
    NO_LOGIN_ERROR(4002,"未登录"),
    NO_AUTH_ERROR(4003,"无权限"),

    SYSTEM_ERROR(5000,"系统内部发生错误"),

    SUCCESS(200,"请求成功");

    private int code;

    private String message;

    BusinessStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
