package com.zero.iweiojbackend.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * ExecuteCodeResponse
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCodeResponse {

    /**
     * 执行状态
     */
    private Integer status;

    /**
     * 信息
     */
    private String message;

    /**
     * 判题信息
     */
    private Collection<JudgeInfo> judgeInfo;

}
