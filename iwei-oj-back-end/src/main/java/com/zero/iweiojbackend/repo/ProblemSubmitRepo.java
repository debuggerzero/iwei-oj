package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitQueryRequest;
import com.zero.iweiojbackend.model.po.ProblemSubmit;

import java.util.List;

/**
 * ProblemSubmitRepo
 *
 * @author ZERO
 * @date 2023/12/30
 */
public interface ProblemSubmitRepo extends BaseRepo<ProblemSubmit> {

    /**
     * 查询所有记录条数
     * @param problemSubmitQuery 查询器
     * @return 记录条数
     */
    Long getTotal(ProblemSubmitQueryRequest problemSubmitQuery);

    /**
     * 查询所有记录
     * @param problemSubmitQuery 查询器
     * @return 结果集
     */
    List<ProblemSubmit> getAll(ProblemSubmitQueryRequest problemSubmitQuery);

}
