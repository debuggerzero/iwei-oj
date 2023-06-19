package com.zero.acskybackend.model.common;

/**
 * 异常处理枚举类
 *
 * @author ZERO
 * @date 2023/6/19
 */
public enum GlobalExceptionEnum {

    /**
     * 账号密码错误
     */
    ACCOUNT_OR_PASSWORD_EXCEPTION(500001, "用户账号或密码错误"),

    /**
     * 无权限访问
     */
    NO_PERMISSION_EXCEPTION(500002, "无权限访问"),

    /**
     * 未登录，无权限访问
     */
    NO_LOG_IN_EXCEPTION(500003, "未登录，无权限访问");

    /**
     * 信息码
     */
    private final Integer code;

    /**
     * 信息
     */
    private final String message;

    GlobalExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
