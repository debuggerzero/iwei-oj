package com.zero.iweiojbackend.judge.codesandbox.impl;

import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;
import com.zero.iweiojbackend.model.common.JudgeInfoMessageEnum;
import com.zero.iweiojbackend.model.common.ProblemSubmitStatusEnum;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;

import java.util.List;

/**
 * 示例沙箱，用于测试
 *
 * @author ZERO
 * @date 2023/12/30
 */
public class ExampleCodeSendBox implements CodeSendBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = (List<String>) executeCodeRequest.getInputs();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputs(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(ProblemSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
