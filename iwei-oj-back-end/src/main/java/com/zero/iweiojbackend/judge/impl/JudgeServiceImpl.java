package com.zero.iweiojbackend.judge.impl;

import cn.hutool.json.JSONUtil;
import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.judge.JudgeManager;
import com.zero.iweiojbackend.judge.JudgeService;
import com.zero.iweiojbackend.judge.codesandbox.CodeSandBoxFactory;
import com.zero.iweiojbackend.judge.codesandbox.CodeSendBox;
import com.zero.iweiojbackend.judge.codesandbox.CodeSendBoxProxy;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeRequest;
import com.zero.iweiojbackend.judge.codesandbox.model.ExecuteCodeResponse;
import com.zero.iweiojbackend.judge.model.JudgeContext;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.JudgeInfoMessageEnum;
import com.zero.iweiojbackend.model.common.ProblemSubmitStatusEnum;
import com.zero.iweiojbackend.judge.codesandbox.model.JudgeInfo;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.service.ProblemSubmitService;
import com.zero.iweiojbackend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JudgeServiceImpl
 *
 * @author ZERO
 * @date 2023/12/31
 */
@Service("judgeServiceImpl")
public class JudgeServiceImpl implements JudgeService {

    @Resource(name = "problemSubmitServiceImpl")
    private ProblemSubmitService problemSubmitService;

    @Resource(name = "probInfoServiceImpl")
    private ProbInfoService probInfoService;

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    @Resource(name = "sampleRepoImpl")
    private SampleRepo sampleRepo;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public ProblemSubmit doJudge(Long problemSubmitId) {
        ProblemSubmit problemSubmit = problemSubmitService.getById(problemSubmitId);
        if (Objects.isNull(problemSubmit)) {
            throw new AssertionException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        // 获取题目信息
        Integer pid = problemSubmit.getProbInfo().getId();
        ProbInfoVO probInfo = probInfoService.queryOneProbInfo(pid);
        if (Objects.isNull(probInfo)) {
            throw new AssertionException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 如果题目状态不为等待中，就不用重复执行
        if (!problemSubmit.getStatus().equals(ProblemSubmitStatusEnum.WAITING.getValue())) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 更改判题状态，防止重复执行
        ProblemSubmit updateProblemSubmit = new ProblemSubmit();
        updateProblemSubmit.setId(problemSubmit.getId());
        updateProblemSubmit.setStatus(ProblemSubmitStatusEnum.RUNNING.getValue());
        Integer update = problemSubmitService.updateById(updateProblemSubmit);
        if (update == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "题目状态更新错误");
        }
        // 调用沙箱
        CodeSendBox codeSendBox = CodeSandBoxFactory.newInstance(type);
        codeSendBox = new CodeSendBoxProxy(codeSendBox);
        String language = problemSubmit.getLanguage();
        String code = problemSubmit.getCode();
        // 获取样例
        Collection<Sample> judgeCaseList = sampleRepo.getAllByProId(pid);
        Collection<String> inputs = judgeCaseList.stream().map(Sample::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputs(inputs)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSendBox.executeCode(executeCodeRequest);
        List<String> outputs = executeCodeResponse.getOutputs();

        // 5）根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setOutputList(outputs);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setProbInfo(probInfo);
        judgeContext.setProblemSubmit(problemSubmit);
        System.out.println(judgeContext);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        updateProblemSubmit = new ProblemSubmit();
        updateProblemSubmit.setId(problemSubmitId);
        updateProblemSubmit.setStatus(ProblemSubmitStatusEnum.SUCCEED.getValue());
        updateProblemSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = problemSubmitService.updateById(updateProblemSubmit);
        if (update == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "题目状态更新错误");
        }
        probInfoService.updateSubmitCnt(probInfo.getId());
        userInfoService.updateSubmitCnt(problemSubmit.getCreatePerson().getId());
        if (judgeInfo.getMessage().equals(JudgeInfoMessageEnum.ACCEPTED.getValue())) {
            probInfoService.updateAcceptCnt(probInfo.getId());
            userInfoService.updateAcceptCnt(problemSubmit.getCreatePerson().getId());
        }
        return problemSubmitService.getById(problemSubmitId);
    }
}
