package com.zero.iweiojbackend.judge;

import com.zero.iweiojbackend.model.po.ProblemSubmit;

/**
 * 判题服务
 *
 * @author ZERO
 * @date 2023/12/30
 */
public interface JudgeService {

    /**
     * 判题
     * @param problemSubmitId id
     * @return 结果
     */
    ProblemSubmit doJudge(Long problemSubmitId);

}
