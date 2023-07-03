package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.ProblemCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.vo.Answer;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import com.zero.acskybackend.service.HistoryService;
import com.zero.acskybackend.service.ProbInfoService;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 用户做题模块
 *
 * @author ZERO
 * @date 2023/7/3
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemInfoController {

    private final ProbInfoService probInfoService;

    private final HistoryService historyService;

    /**
     * 获取题目总数
     *
     * @return 总数
     */
    @GetMapping("/total")
    public Long queryProbInfoTotal() {
        return probInfoService.queryTotal();
    }

    /**
     * 获取题目列表
     *
     * @param pageNum  页号
     * @param pageSize 页数
     * @return 题目列表
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public List<ProbInfoVO> queryProbInfoVOList(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        return probInfoService.queryProbInfoVOList(new Page(pageNum, pageSize));
    }

    /**
     * 获取单个题目详细信息
     *
     * @param problemId 题号
     * @return 题目信息
     */
    @GetMapping("/one/{problemId}")
    public ProbInfo queryProbInfo(@PathVariable Integer problemId) {
        return probInfoService.queryOneProbInfo(problemId);
    }

    /**
     * 调试题目
     *
     * @param problemCommand problemCommand
     * @return 答案
     */
    @PostMapping("/debug")
    public Answer debugProb(@RequestBody ProblemCommand problemCommand) {
        if (Objects.isNull(problemCommand) || StringUtil.isEmpty(problemCommand.getType())) {
            throw new AssertionException(GlobalExceptionEnum.SERVER_ERROR);
        }
        return probInfoService.taseAndRun(problemCommand);
    }

    /**
     * 提交题目
     *
     * @param problemCommand problemCommand
     * @return 答案
     */
    @PostMapping("/commit")
    public Answer commitAndRun(@RequestBody ProblemCommand problemCommand) {
        if (Objects.isNull(problemCommand) ||
                problemCommand.getPid() == null ||
                problemCommand.getUid() == null ||
                StringUtil.isEmpty(problemCommand.getType())) {
            throw new AssertionException(GlobalExceptionEnum.SERVER_ERROR);
        }
        return probInfoService.commitAndRun(problemCommand);
    }

    /**
     * 获取用户历史记录
     */
    @GetMapping("/history/{uid}")
    public List<History> queryHistoryList(@PathVariable Integer uid) {
        if (uid == null) {
            throw new AssertionException(GlobalExceptionEnum.SERVER_ERROR);
        }
        return historyService.queryHistoryList(uid);
    }

}
