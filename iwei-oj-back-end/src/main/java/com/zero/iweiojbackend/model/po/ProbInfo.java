package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 题目类型
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@Builder
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
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 标签信息列表
     */
    private List<TagInfo> tagInfos;

}
