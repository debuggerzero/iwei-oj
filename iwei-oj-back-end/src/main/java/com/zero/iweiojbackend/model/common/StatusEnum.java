package com.zero.iweiojbackend.model.common;

import lombok.Getter;

/**
 * 题目状态枚举
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Getter
public enum StatusEnum {

    /**
     * 操作成功
     */
    SUCCESS(-1, "Success"),

    /**
     * 答案错误
     */
    WRONG_ANSWER(0, "Wrong Answer"),

    /**
     * 接受
     */
    ACCEPT(1, "Accept"),

    /**
     * 运行
     */
    TIME_OUT(2, "Time Out"),

    /**
     * 编译错误
     */
    COMPILE_ERROR(3, "Compile Error"),

    /**
     * 异常
     */
    EXCEPTION(4, "Exception");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 信息
     */
    private final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
