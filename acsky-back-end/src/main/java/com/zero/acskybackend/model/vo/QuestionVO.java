package com.zero.acskybackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProblemInfoVO
 *
 * @author ZERO
 * @date 2023/7/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVO {

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
     * 时间限制(ms)
     */
    private Integer timeLimit;

    /**
     * 空间限制(kb)
     */
    private Integer spaceLimit;

    /**
     * 题目内容
     */
    private String context;

    /**
     * 通过数
     */
    private Integer passCnt;

    /**
     * 提交数
     */
    private Integer submitCnt;

}
