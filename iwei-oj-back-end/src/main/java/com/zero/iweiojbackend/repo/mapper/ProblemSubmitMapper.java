package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitQueryRequest;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目提交记录
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Mapper
public interface ProblemSubmitMapper {

    /**
     * 保存题目提交记录
     * @param problemSubmit 题目记录
     * @return 受影响的行数
     */
    Integer save(ProblemSubmit problemSubmit);

    /**
     * 通过 id 修改信息
     * @param problemSubmit 题目记录
     * @return 受影响的行数
     */
    Integer updateById(ProblemSubmit problemSubmit);

    /**
     * 通过 id 获取判题信息
     * @param id id
     * @return 结果
     */
    ProblemSubmit getById(@Param("id") Number id);

    /**
     * 查询所有记录
     * @param problemSubmitQuery 查询器
     * @return 结果集
     */
    List<ProblemSubmit> getAll(ProblemSubmitQueryRequest problemSubmitQuery);

    /**
     * 获取总记录数
     * @param problemSubmitQuery 查询器
     * @return 结果集
     */
    Long getTotal(ProblemSubmitQueryRequest problemSubmitQuery);
}
