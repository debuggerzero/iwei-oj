package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * ProbInfoService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface ProbInfoService {

    /**
     * 查询问题信息列表
     * @param query 分页
     * @return GeneralCollectionResult<ProbInfoVO>
     */
    GeneralCollectionResult<ProbInfoVO> queryProbInfoVOList(BaseQuery query);

    /**
     * 查询问题信息列表（管理员）
     * @param query 查询器
     * @return List<ProbInfo>
     */
    GeneralCollectionResult<ProbInfo> queryProbInfoList(BaseQuery query);

    /**
     * 通过题目查询所有用例（管理员）
     * @param probId 题目 id
     * @return 结果集
     */
    GeneralCollectionResult<Sample> querySampleList(Integer probId);

    /**
     * 查询问题详细信息
     * @param probId 问题 id
     * @return ProbInfoVO
     */
    ProbInfoVO queryOneProbInfo(Integer probId);

    /**
     * 添加题目（管理员）
     * @param problemRequest 题目信息
     * @param request request
     * @return 受影响的行数
     */
    Integer save(ProblemRequest problemRequest, HttpServletRequest request);

    /**
     * 通过 id 更新题目信息（管理员）
     * @param problemRequest 题目信息
     * @param request request
     * @return 受影响的行数
     */
    Integer updateById(ProblemRequest problemRequest, HttpServletRequest request);

    /**
     * 更新提交次数
     * @param pid 题目 id
     * @return 受影响的行数
     */
    Integer updateSubmitCnt(Integer pid);

    /**
     * 更新通过次数
     * @param pid 题目 id
     * @return 受影响的行数
     */
    Integer updateAcceptCnt(Integer pid);

    /**
     * 通过 id 删除题目信息（管理员）
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

}
