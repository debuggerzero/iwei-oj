package com.zero.acskybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常类
 *
 * @author ZERO
 * @date 2023/06/14
 */
@Getter
public class AssertionException extends RuntimeException {

    /**
     * 错误代码
     */
    private final Integer code;

    /**
     * http 状态码
     */
    private final Integer httpStatus;

    /**
     * message 信息
     */
    private final String message;

    /**
     * 异常
     *
     * @param status     http 状态码
     * @param code       异常 code
     * @param message    异常信息
     */
    public AssertionException(HttpStatus status, Integer code, String message) {
        super(message);
        this.httpStatus = status.value();
        this.code = code;
        this.message = message;
    }

    /**
     * 异常 默认返回 500 http 状态码
     *
     * @param code    异常 code
     * @param message 异常信息
     */
    public AssertionException(Integer code, String message) {
        super(message);
        this.code = code;
        this.httpStatus = 500;
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        // 重写方法，禁止抓堆栈信息；提高效率
        return this;
    }

}
