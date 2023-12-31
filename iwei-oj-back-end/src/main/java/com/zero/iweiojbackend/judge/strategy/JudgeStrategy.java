package com.zero.iweiojbackend.judge.strategy;

import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;

/**
 * 判题策略
 *
 * @author ZERO
 * @date 2023/12/31
 */
public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext 判题上下文
     * @return 判题信息
     */
    JudgeInfo doJudge(JudgeContext judgeContext);

}
