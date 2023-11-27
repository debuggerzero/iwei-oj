package com.zero.acskybackend.service;

import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.request.ProblemRequest;
import com.zero.acskybackend.model.vo.Answer;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import com.zero.acskybackend.model.vo.QuestionVO;

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
     * @param page 分页
     * @return List<ProbInfoVO>
     */
    List<ProbInfoVO> queryProbInfoVOList(Page page);

    /**
     * 查询问题详细信息（Markdown）
     * @param probId 问题 id
     * @return QuestionVO
     */
    QuestionVO queryOneQuestionVO(Integer probId);

    /**
     * 查询问题详细信息
     * @param probId 问题 id
     * @return ProbInfo
     */
    ProbInfo queryOneProbInfo(Integer probId);

    /**
     * 调试
     * @param problemCommand problemCommand
     * @return Answer
     */
    Answer taseAndRun(ProblemRequest problemCommand);

    /**
     * 提交
     * @param problemCommand problemCommand
     * @return Answer
     */
    Answer commitAndRun(ProblemRequest problemCommand);

}
