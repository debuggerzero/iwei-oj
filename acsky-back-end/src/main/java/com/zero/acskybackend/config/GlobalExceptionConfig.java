package com.zero.acskybackend.config;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.common.BaseResponse;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.utils.ResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义全局异常
 *
 * @author ZERO
 * @date 2022/12/30
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionConfig {

    @ExceptionHandler(value = AssertionException.class)
    public BaseResponse<?> assertionExceptionHandler(Exception e) {
        log.error("AssertionException", e);
        return ResultUtils.error(((AssertionException) e).getCode(), e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(Exception e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

}
