package com.zero.iweiojbackend.judge.strategy.impl;

import com.zero.iweiojbackend.judge.strategy.JudgeStrategy;
import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.model.common.JudgeInfoMessageEnum;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;
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
     * @param judgeContext 判题上下文
     * @return 判题信息
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<String> outputList = (List<String>) judgeContext.getOutputList();
        ProbInfoVO probInfo = judgeContext.getProbInfo();
        List<Sample> judgeCaseList = (List<Sample>) judgeContext.getJudgeCaseList();

        // 设置默认的状态为通过
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        // 先判断沙箱执行的结果输出数量是否与预期相等
        if (outputList.size() != judgeCaseList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 依次判断每个输出与预期是否相等
        for (int i = 0; i < judgeCaseList.size(); i ++) {
            Sample sample = judgeCaseList.get(i);
            if (!sample.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制
        Integer needTimeLimit = probInfo.getTimeLimit();
        Integer spaceLimit = probInfo.getSpaceLimit();
        // 判断内存是否超出
        if (memory > spaceLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        // 判断时间是否超出
        if (time > needTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }

}
