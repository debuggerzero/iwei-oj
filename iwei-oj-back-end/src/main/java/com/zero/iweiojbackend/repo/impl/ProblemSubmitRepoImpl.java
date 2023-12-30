package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.dto.questionsubmit.ProblemSubmitQueryRequest;
import com.zero.iweiojbackend.model.po.ProblemSubmit;
import com.zero.iweiojbackend.repo.ProblemSubmitRepo;
import com.zero.iweiojbackend.repo.mapper.ProblemSubmitMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProblemSubmitRepoImpl
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Repository("problemSubmitRepoImpl")
public class ProblemSubmitRepoImpl implements ProblemSubmitRepo {

    @Resource
    private ProblemSubmitMapper problemSubmitMapper;

    @Override
    public Integer save(ProblemSubmit problemSubmit) {
        return problemSubmitMapper.save(problemSubmit);
    }

    @Override
    public Integer updateById(ProblemSubmit problemSubmit) {
        return problemSubmitMapper.updateById(problemSubmit);
    }

    @Override
    public ProblemSubmit getById(Number id) {
        return problemSubmitMapper.getById(id);
    }

    @Override
    public Long getTotal(ProblemSubmitQueryRequest problemSubmitQuery) {
        return problemSubmitMapper.getTotal(problemSubmitQuery);
    }

    @Override
    public List<ProblemSubmit> getAll(ProblemSubmitQueryRequest problemSubmitQuery) {
        return problemSubmitMapper.getAll(problemSubmitQuery);
    }
}
