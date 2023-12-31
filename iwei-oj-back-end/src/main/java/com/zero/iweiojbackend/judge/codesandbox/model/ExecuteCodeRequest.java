package com.zero.iweiojbackend.judge.codesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * ExecuteCodeRequest
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {

    /**
     * 输入用例
     */
    private Collection<String> inputs;

    /**
     * 代码
     */
    private String code;

    /**
     * 编程语言
     */
    private String language;

}
