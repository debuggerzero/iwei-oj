package com.zero.iweiojbackend.model.dto.questionsubmit;

import com.zero.iweiojbackend.model.common.Page;
import lombok.*;

/**
 * 提交记录查询请求
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemSubmitQueryRequest extends Page {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交状态
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Integer pid;

    /**
     * 用户 id
     */
    private Integer uid;

}
