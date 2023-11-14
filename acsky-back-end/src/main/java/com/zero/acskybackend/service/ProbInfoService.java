package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.request.ProblemRequest;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.common.StatusEnum;
import com.zero.acskybackend.model.converter.ToHistoryConverter;
import com.zero.acskybackend.model.converter.ToProbInfoVOConverter;
import com.zero.acskybackend.model.converter.ToQuestionVOConverter;
import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.po.Sample;
import com.zero.acskybackend.model.vo.Answer;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import com.zero.acskybackend.model.vo.QuestionVO;
import com.zero.acskybackend.repo.ProbInfoRepo;
import com.zero.acskybackend.utils.TaskUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ProbService
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProbInfoService {

    @Resource(name = "probInfoRepoImpl")
    private final ProbInfoRepo probInfoRepo;

    private final SampleService sampleService;

    private final HistoryService historyService;

    public Long queryTotal() {
        return probInfoRepo.queryTotal();
    }

    public List<ProbInfoVO> queryProbInfoVOList(Page page) {
        if (Objects.isNull(page)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        List<ProbInfo> allList = probInfoRepo.queryAllList();
        if (allList == null) {
            return Collections.emptyList();
        }
        return allList.stream()
                .map(ToProbInfoVOConverter.CONVERTER::toProbInfoVO)
                .filter(Objects::nonNull)
                .skip((page.getPageNumber() > 0 && page.getPageSize() > 0) ? (page.getPageNumber() - 1) * page.getPageSize() : 0)
                .limit(page.getPageSize())
                .collect(Collectors.toList());
    }

    public QuestionVO queryOneQuestionVO(Integer probId) {
        ProbInfo probInfo = probInfoRepo.queryOne(probId);

        StringBuilder builder = new StringBuilder();
        builder.append(probInfo.getDescription());
        builder.append("\n\n");
        if (probInfo.getInputDesc() != null) {
            builder.append("**输入描述：**\n- ");
            builder.append(probInfo.getInputDesc());
            builder.append("\n\n");
        }
        if (probInfo.getInputDesc() != null) {
            builder.append("**输出描述：**\n- ");
            builder.append(probInfo.getOutputDesc());
            builder.append("\n\n");
        }
        for (int i = 0; i < probInfo.getSamples().size(); i++) {
            Sample sample = probInfo.getSamples().get(i);

            if (sample.getInput() != null) {
                builder.append("**输入样例：**\n");
                builder.append("```cpp\n");
                builder.append(sample.getInput());
                builder.append("\n```\n");
            }
            if (sample.getOutput() != null) {
                builder.append("**输出样例：**\n");
                builder.append("```cpp\n");
                builder.append(sample.getOutput());
                builder.append("\n```\n");
            }
        }
        if (probInfo.getHint() != null) {
            builder.append("**提示：**\n");
            builder.append(probInfo.getHint());
            builder.append("\n");
        }

        QuestionVO questionVO = ToQuestionVOConverter.CONVERTER.toQuestionVO(probInfo);
        questionVO.setContext(builder.toString());
        return questionVO;
    }

    public ProbInfo queryOneProbInfo(Integer probId) {
        return probInfoRepo.queryOne(probId);
    }

    public Answer taseAndRun(ProblemRequest problemCommand) {
        Answer answer;
        try {
            answer = TaskUtil.compileAndRun(problemCommand);
        } catch (InterruptedException | IOException e) {
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        return answer;
    }

    public Answer commitAndRun(ProblemRequest problemCommand) {
        List<Sample> samples = sampleService.querySampleByProbId(problemCommand.getPid());
        Answer answer = null;
        boolean flag = true;
        for (int i = 0; i < samples.size() && flag; i++) {
            Sample sample = samples.get(i);
            problemCommand.setTase(sample.getInput());
            answer = taseAndRun(problemCommand);
            if (!answer.getMessage().equals(StatusEnum.SUCCESS.getMessage())) {
                flag = false;
                continue;
            }
            if (!sample.getOutput().trim().equals(answer.getStdout().trim())) {
                answer.setCode(StatusEnum.WRONG_ANSWER.getCode());
                answer.setMessage(StatusEnum.WRONG_ANSWER.getMessage());
                answer.setStdout("");
                flag = false;
            }
        }
        assert answer != null;
        if (flag) {
            answer.setCode(StatusEnum.ACCEPT.getCode());
            answer.setMessage(StatusEnum.ACCEPT.getMessage());
            answer.setStdout("");
        }
        Integer result = historyService.insertHistory(ToHistoryConverter.CONVERTER.toProbInfoVO(problemCommand, answer));
        if (result == 0) {
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        return answer;
    }

}
