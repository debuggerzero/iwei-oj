package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 题目提交
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemSubmit {

    /**
     * 记录 id
     */
    private Long id;

    /**
     * 语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json 对象）
     */
    private String judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

    /**
     * 题目信息
     */
    private ProbInfo probInfo;

    /**
     * 创建人
     */
    private UserInfo createPerson;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

}
