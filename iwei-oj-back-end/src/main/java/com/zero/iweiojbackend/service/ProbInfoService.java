package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.question.ProblemRequest;
import com.zero.iweiojbackend.model.vo.Answer;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;

import java.util.List;

/**
 * ProbInfoService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface ProbInfoService {

    /**
     * 查询问题总数
     * @return 总条数
     */
    Long queryTotal();

    /**
     * 查询问题信息列表
     * @param query 分页
     * @return List<ProbInfoVO>
     */
    List<ProbInfoVO> queryProbInfoVOList(BaseQuery query);

    /**
     * 查询问题信息列表（管理员）
     * @param query 查询器
     * @return List<ProbInfo>
     */
    List<ProbInfo> queryProbInfoList(BaseQuery query);

    /**
     * 查询问题详细信息
     * @param probId 问题 id
     * @return ProbInfoVO
     */
    ProbInfoVO queryOneProbInfo(Integer probId);

    /**
     * 添加题目（管理员）
     * @param problemRequest 题目信息
     * @return 受影响的行数
     */
    Integer save(ProblemRequest problemRequest);

    /**
     * 通过 id 更新题目信息（管理员）
     * @param problemRequest 题目信息
     * @return 受影响的行数
     */
    Integer updateById(ProblemRequest problemRequest);

    /**
     * 通过 id 删除题目信息（管理员）
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

}
