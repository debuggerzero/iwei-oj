package com.zero.acskybackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 题目类型
 *
 * @author wjh
 * @date 2023/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProbInfo {

    /**
     * id
     */
    private Integer id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目难度
     */
    private Integer difficulty;

    /**
     * 通过数
     */
    private Integer passCnt;

    /**
     * 提交数
     */
    private Integer submitCnt;

    /**
     * 时间限制(ms)
     */
    private Integer timeLimit;

    /**
     * 空间限制(kb)
     */
    private Integer spaceLimit;

    /**
     * 题目描述
     */
    private String description;

    /**
     * 输入格式描述
     */
    private String inputDesc;

    /**
     * 输出格式描述
     */
    private String outputDesc;

    /**
     * 样例
     */
    private List<Sample> samples;

    /**
     * 提示信息
     */
    private String hint;

}
