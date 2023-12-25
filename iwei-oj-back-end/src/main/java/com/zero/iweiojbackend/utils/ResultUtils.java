package com.zero.iweiojbackend.utils;

import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;

/**
 * 返回工具类
 *
 * @author ZERO
 * @date 2023/08/23
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 数据
     * @param <T> 类型
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return BaseResponse
     */
    public static BaseResponse<Object> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code 错误码
     * @param message 信息
     * @return BaseResponse<Object>
     */
    public static BaseResponse<Object> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @param message 信息
     * @return BaseResponse<Object>
     */
    public static BaseResponse<Object> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}
