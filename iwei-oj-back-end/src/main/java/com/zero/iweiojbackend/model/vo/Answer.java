package com.zero.iweiojbackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wjh
 * @date 2023/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    /**
     * 输出内容
     */
    private String stdout;
}
