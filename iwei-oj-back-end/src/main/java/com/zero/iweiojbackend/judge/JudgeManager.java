package com.zero.iweiojbackend.judge;

import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.judge.strategy.JudgeStrategy;
import com.zero.iweiojbackend.judge.strategy.impl.DefaultJudgeStrategy;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理，简化调用
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Service
public class JudgeManager {

    public JudgeInfo doJudge(JudgeContext judgeContext) {
        ProblemSubmit problemSubmit = judgeContext.getProblemSubmit();
        String language = problemSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        return judgeStrategy.doJudge(judgeContext);
    }

}
