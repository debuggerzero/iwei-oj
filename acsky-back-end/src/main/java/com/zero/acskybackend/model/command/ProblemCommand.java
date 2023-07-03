package com.zero.acskybackend.model.command;

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
public class ProblemCommand {

    /**
     * 题目 id
     */
    private Integer pid;

    /**
     * 做题人 id
     */
    private Integer uid;

    /**
     * 要编译和执行的代码
     */
    private String code;

    /**
     * 输入
     */
    private String tase;

    /**
     * 执行语种类别
     */
    private String type;

    /**
     * 时间
     */
    private Integer timeLimit;

    /**
     * 空间
     */
    private Integer spaceLimit;
}
