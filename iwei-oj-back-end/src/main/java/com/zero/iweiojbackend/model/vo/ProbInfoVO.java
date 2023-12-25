package com.zero.iweiojbackend.model.vo;

import com.zero.iweiojbackend.model.po.TagInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Integer acceptCnt;

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
     * 标签信息列表
     */
    private List<TagInfo> tagInfos;
}
