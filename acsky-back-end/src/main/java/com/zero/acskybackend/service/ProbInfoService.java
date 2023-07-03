package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.ProblemCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.common.StatusEnum;
import com.zero.acskybackend.model.converter.ToHistoryConverter;
import com.zero.acskybackend.model.converter.ToProbInfoVOConverter;
import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.po.Sample;
import com.zero.acskybackend.model.vo.Answer;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import com.zero.acskybackend.repo.ProbInfoRepo;
import com.zero.acskybackend.utils.TaskUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
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
        return probInfoRepo.queryAllList()
                .stream()
                .map(ToProbInfoVOConverter.CONVERTER::toProbInfoVO)
                .skip((page.getPageNumber() - 1) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList());
    }

    public ProbInfo queryOneProbInfo(Integer probId) {
        return probInfoRepo.queryOne(probId);
    }

    public Answer taseAndRun(ProblemCommand problemCommand) {
        Answer answer;
        try {
            answer = TaskUtil.compileAndRun(problemCommand);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new AssertionException(GlobalExceptionEnum.SERVER_ERROR);
        }
        return answer;
    }

    public Answer commitAndRun(ProblemCommand problemCommand) {
        List<Sample> samples = sampleService.querySampleByProbId(problemCommand.getPid());
        Answer answer = null;
        boolean flag = true;
        for (int i = 0; i < samples.size() && flag; i++) {
            Sample sample = samples.get(i);
            problemCommand.setTase(sample.getInput());
            answer = taseAndRun(problemCommand);
            if (!answer.getMessage().equals(StatusEnum.SUCCESS.getMessage())) {
                flag = false;
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
            throw new AssertionException(GlobalExceptionEnum.SERVER_ERROR);
        }
        return answer;
    }

}