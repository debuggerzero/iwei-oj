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
public class ProblemSubmitAddRequest extends Page {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Integer pid;

}
