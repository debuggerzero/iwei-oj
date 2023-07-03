package com.zero.acskybackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProbInfoVO
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProbInfoVO {

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

}
