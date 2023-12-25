package com.zero.iweiojbackend.model.dto.question;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.po.Sample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ProblemRequest
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemRequest {

    /**
     * 操作人 id
     */
    private Integer uid;

    /**
     * 题目信息
     */
    private ProbInfo probInfo;

    /**
     * 用例集
     */
    private List<Sample> samples;

}
