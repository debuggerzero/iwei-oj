package com.zero.iweiojbackend.judge.model;

import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import lombok.Data;

import java.util.Collection;

/**
 * 判题上下文
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private Collection<String> outputList;

    private Collection<Sample> judgeCaseList;

    private ProbInfoVO probInfo;

    private ProblemSubmit problemSubmit;

}
