package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.service.HistoryService;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.po.History;
import com.zero.iweiojbackend.model.vo.Answer;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 用户做题模块
 *
 * @author ZERO
 * @date 2023/12/24
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
     * @param baseQuery 查询器
     * @return 题目列表
     */
    @PostMapping("/list")
    public BaseResponse<List<ProbInfoVO>> queryProbInfoVOList(@RequestBody BaseQuery baseQuery) {
        if (Objects.isNull(baseQuery)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Page page = baseQuery.getPage();
        if (Objects.isNull(page) || Objects.isNull(page.getPageNumber()) || Objects.isNull(page.getPageSize()) || page.getPageNumber() <= 0 || page.getPageSize() <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(probInfoService.queryProbInfoVOList(baseQuery));
    }

    /**
     * 获取题目详细信息
     *
     * @param problemId 题号
     * @return 题目信息
     */
    @GetMapping("/one/{problemId}")
    @Deprecated
    public BaseResponse<ProbInfoVO> queryProbInfo(@PathVariable Integer problemId) {
        if (Objects.isNull(problemId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(probInfoService.queryOneProbInfo(problemId));
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
