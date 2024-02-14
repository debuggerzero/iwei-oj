package com.zero.iweiojbackend.judge.strategy.impl;

import com.zero.iweiojbackend.judge.codesandbox.model.JudgeResult;
import com.zero.iweiojbackend.judge.strategy.JudgeStrategy;
import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;
import com.zero.iweiojbackend.model.common.JudgeEnum;
import com.zero.iweiojbackend.model.common.LanguageEnum;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 判题策略
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Service("defaultJudgeStrategy")
public class DefaultJudgeStrategy implements JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext 判题上下文
     * @return 判题信息
     */
    @Override
    public JudgeResult doJudge(JudgeContext judgeContext) {

        ProbInfoVO probInfo = judgeContext.getProbInfo();
        List<JudgeInfo> judgeInfo = (List<JudgeInfo>) judgeContext.getJudgeInfo();
        List<Sample> judgeCaseList = (List<Sample>) judgeContext.getJudgeCaseList();
        String language = judgeContext.getProblemSubmit().getLanguage();

        JudgeResult judgeInfoResponse = new JudgeResult();
        // 设置默认的状态为通过
        JudgeEnum judgeEnum = JudgeEnum.ACCEPTED;
        if (judgeInfo.size() != judgeCaseList.size()) {
            judgeEnum = JudgeEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeEnum.getMessage());
            return judgeInfoResponse;
        }
        long memory = 0L, time = 0L;
        for (JudgeInfo item : judgeInfo) {
            assert judgeEnum != null;
            if (item.getStatus() > judgeEnum.getValue()) {
                judgeEnum = JudgeEnum.getEnumByValue(item.getStatus());
            }
            memory = Math.max(memory, item.getMemoryUsed());
            time = Math.max(time, item.getTimeUsed());
        }
        long bit = 1024L;
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        // 判断题目限制
        LanguageEnum languageEnum = LanguageEnum.getEnumByValue(language);
        Integer needTimeLimit = probInfo.getTimeLimit();
        Integer spaceLimit = probInfo.getSpaceLimit();
        if (!LanguageEnum.C.equals(languageEnum) && !LanguageEnum.CPP.equals(languageEnum)) {
            needTimeLimit *= 2;
            spaceLimit *= 2;
        }

        // 判断内存是否超出
        if (memory > spaceLimit * bit) {
            assert judgeEnum != null;
            judgeEnum = JudgeEnum.getEnumByValue(Math.max(JudgeEnum.MEMORY_LIMIT_EXCEEDED.getValue(), judgeEnum.getValue()));
        }
        // 判断时间是否超出
        if (time > needTimeLimit) {
            assert judgeEnum != null;
            judgeEnum = JudgeEnum.getEnumByValue(Math.max(JudgeEnum.TIME_LIMIT_EXCEEDED.getValue(), judgeEnum.getValue()));
        }
        assert judgeEnum != null;
        if (!judgeEnum.equals(JudgeEnum.ACCEPTED)) {
            judgeInfoResponse.setMessage(judgeEnum.getMessage());
            return judgeInfoResponse;
        }
        // 依次判断每个输出与预期是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            Sample sample = judgeCaseList.get(i);
            if (!sample.getOutput().equals(judgeInfo.get(i).getOutput())) {
                judgeEnum = JudgeEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeEnum.getMessage());
                return judgeInfoResponse;
            }
        }
        judgeInfoResponse.setMessage(judgeEnum.getMessage());
        return judgeInfoResponse;
    }

}
