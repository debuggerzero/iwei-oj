package com.zero.iweiojbackend.model.dto.question;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.po.Sample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
     * 题目信息
     */
    private ProbInfo probInfo;

    /**
     * 用例集
     */
    private List<Sample> samples;

    public static boolean isNull(ProblemRequest problemRequest) {
        if (Objects.isNull(problemRequest) ||
                Objects.isNull(problemRequest.getProbInfo())) {
            return true;
        }
        if (Objects.isNull(problemRequest.getSamples())) {
            problemRequest.setSamples(Collections.emptyList());
        }
        return false;
    }

}
