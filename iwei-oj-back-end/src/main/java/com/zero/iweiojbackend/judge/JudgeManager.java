package com.zero.iweiojbackend.judge;

import com.zero.iweiojbackend.judge.codesandbox.model.JudgeResult;
import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.judge.strategy.JudgeStrategy;
import com.zero.iweiojbackend.judge.strategy.impl.DefaultJudgeStrategy;
import org.springframework.stereotype.Service;

/**
 * 判题管理，简化调用
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Service
public class JudgeManager {

    public JudgeResult doJudge(JudgeContext judgeContext) {
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        return judgeStrategy.doJudge(judgeContext);
    }

}
