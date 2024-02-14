package com.zero.iweiojbackend.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题信息
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeInfo {

    /**
     * 状态
     */
    private Integer status;

    /**
     * 消耗内存
     */
    private Long memoryUsed;

    /**
     * 消耗时间
     */
    private Long timeUsed;

    /**
     * 输出
     */
    private String output;

    /**
     * 错误信息
     */
    private String errorMessage;

}
