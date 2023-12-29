package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.service.ProbInfoService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 题目模块
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

    /**
     * 查询问题信息列表（管理员）
     * @param query 查询器
     * @return List<ProbInfo>
     */
    @PostMapping("/getProbInfoList")
    public BaseResponse<GeneralCollectionResult<ProbInfo>> queryProbInfoList(@RequestBody BaseQuery query) {
        return ResultUtils.success(probInfoService.queryProbInfoList(query));
    }

    /**
     * 获取题目列表
     *
     * @param baseQuery 查询器
     * @return 题目列表
     */
    @PostMapping("/getProbInfoVOList")
    public BaseResponse<GeneralCollectionResult<ProbInfoVO>> queryProbInfoVOList(@RequestBody BaseQuery baseQuery) {
        return ResultUtils.success(probInfoService.queryProbInfoVOList(baseQuery));
    }

    /**
     * 获取题目详细信息
     *
     * @param proId 题号
     * @return 题目信息
     */
    @GetMapping("/one/{proId}")
    @Deprecated
    public BaseResponse<ProbInfoVO> queryProbInfo(@PathVariable Integer proId) {
        return ResultUtils.success(probInfoService.queryOneProbInfo(proId));
    }

    /**
     * 添加题目（管理员）
     * @param problemRequest 题目信息
     * @return 受影响的行数
     */
    @PostMapping("/save")
    public BaseResponse<Integer> save(@RequestBody ProblemRequest problemRequest) {
        return ResultUtils.success(probInfoService.save(problemRequest));
    }

    /**
     * 通过 id 更新题目信息（管理员）
     * @param problemRequest 题目信息
     * @return 受影响的行数
     */
    @PutMapping ("/update")
    public BaseResponse<Integer> updateById(@RequestBody ProblemRequest problemRequest) {
        return ResultUtils.success(probInfoService.updateById(problemRequest));
    }

    /**
     * 通过 id 删除题目信息（管理员）
     * @param proId 题目 id
     * @return 受影响的行数
     */
    @DeleteMapping("/delete/{proId}")
    public BaseResponse<Integer> deleteById(@PathVariable Integer proId) {
        return ResultUtils.success(probInfoService.deleteById(proId));
    }


}
