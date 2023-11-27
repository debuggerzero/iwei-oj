package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.request.ProblemRequest;
import com.zero.acskybackend.model.common.BaseResponse;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.service.HistoryService;
import com.zero.acskybackend.service.ProbInfoService;
import com.zero.acskybackend.utils.ResultUtils;
import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.vo.Answer;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import com.zero.acskybackend.model.vo.QuestionVO;
import com.zero.acskybackend.service.impl.HistoryServiceImpl;
import com.zero.acskybackend.service.impl.ProbInfoServiceImpl;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource(name = "probInfoServiceImpl")
    private ProbInfoService probInfoService;

    @Resource(name = "historyServiceImpl")
    private final HistoryService historyService;

    /**
     * 获取题目总数
     *
     * @return 总数
     */
    @GetMapping("/total")
    public BaseResponse<Long> queryProbInfoTotal() {
        return ResultUtils.success(probInfoService.queryTotal());
    }

    /**
     * 获取题目列表
     *
     * @param pageNum  页号
     * @param pageSize 页数
     * @return 题目列表
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public BaseResponse<List<ProbInfoVO>> queryProbInfoVOList(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        if (Objects.isNull(pageNum) || Objects.isNull(pageSize) || pageNum <= 0 || pageSize <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(probInfoService.queryProbInfoVOList(new Page(pageNum, pageSize)));
    }

    /**
     * 获取题目详细信息
     *
     * @param problemId 题号
     * @return 题目信息
     */
    @GetMapping("/one/{problemId}")
    @Deprecated
    public BaseResponse<ProbInfo> queryProbInfo(@PathVariable Integer problemId) {
        if (Objects.isNull(problemId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(probInfoService.queryOneProbInfo(problemId));
    }

    /**
     * 获取题目详细信息(Markdown 生成)
     * @param problemId 题号
     * @return 题目信息
     */
    @GetMapping("/oneByMd/{problemId}")
    public BaseResponse<QuestionVO> queryQuestionVO(@PathVariable Integer problemId) {
        if (Objects.isNull(problemId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(probInfoService.queryOneQuestionVO(problemId));
    }

    /**
     * 调试题目
     *
     * @param problemRequest problemRequest
     * @return 答案
     */
    @PostMapping("/debug")
    public BaseResponse<Answer> debugProb(@RequestBody ProblemRequest problemRequest) {
        isNullProblemRequest(problemRequest);
        return ResultUtils.success(probInfoService.taseAndRun(problemRequest));
    }

    /**
     * 提交题目
     *
     * @param problemRequest problemRequest
     * @return 答案
     */
    @PostMapping("/commit")
    public BaseResponse<Answer> commitAndRun(@RequestBody ProblemRequest problemRequest) {
        isNullProblemRequest(problemRequest);
        return ResultUtils.success(probInfoService.commitAndRun(problemRequest));
    }

    private void isNullProblemRequest(@RequestBody ProblemRequest problemRequest) {
        // 对象为空
        if (Objects.isNull(problemRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 题目 id 为空
        if (Objects.isNull(problemRequest.getPid())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 用户 id 为空
        if (Objects.isNull(problemRequest.getUid())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 语种类型 为空
        if (StringUtil.isEmpty(problemRequest.getType())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 获取用户做题记录
     * @param uid 用户 id
     * @return 记录列表
     */
    @GetMapping("/history/{uid}")
    public BaseResponse<List<History>> queryHistoryList(@PathVariable Integer uid) {
        if (Objects.isNull(uid)) {
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(historyService.queryHistoryList(uid));
    }

}
