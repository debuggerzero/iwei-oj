package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitAddRequest;
import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitQueryRequest;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.ProblemSubmitVO;
import com.zero.iweiojbackend.model.vo.UserInfoVO;

/**
 * ProblemSubmitService
 *
 * @author ZERO
 * @date 2023/12/30
 */
public interface ProblemSubmitService {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser 登录用户
     * @return 判题记录 id
     */
    Long doQuestionSubmit(ProblemSubmitAddRequest questionSubmitAddRequest, UserInfoVO loginUser);

    /**
     * 通过 id 查询 problemSubmit
     * @param problemSubmit problemSubmit
     * @return 受影响的行数
     */
    Integer updateById(ProblemSubmit problemSubmit);

    /**
     * 通过 id 查询判题信息
     * @param id 判题 id
     * @return 判题记录信息
     */
    ProblemSubmit getById(Integer id);

    /**
     * 获取提交记录列表
     * @param problemSubmitQueryRequest 记录查询信息
     * @param loginUser 登录用户
     * @return 结果集
     */
    GeneralCollectionResult<ProblemSubmitVO> getProblemSubmitVO(ProblemSubmitQueryRequest problemSubmitQueryRequest, UserInfoVO loginUser);

}
