package com.zero.acskybackend.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FailureResponseBody
 *
 * @author ZERO
 * @date 2023/06/14
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class FailureResponseBody {
    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;
}