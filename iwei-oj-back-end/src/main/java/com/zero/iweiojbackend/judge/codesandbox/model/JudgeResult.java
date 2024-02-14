package com.zero.iweiojbackend.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题结果
 *
 * @author ZERO
 * @date 2024/2/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeResult {

    /**
     * 判题信息
     */
    private String message;

    /**
     * 判题空间
     */
    private Long memory;

    /**
     * 判题时间
     */
    private Long time;

}
