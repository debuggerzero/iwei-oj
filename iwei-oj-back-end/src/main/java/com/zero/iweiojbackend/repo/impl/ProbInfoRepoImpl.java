package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.repo.ProbInfoRepo;
import com.zero.iweiojbackend.repo.mapper.ProbInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author ZERO
 */
@RequiredArgsConstructor
@Repository("probInfoRepoImpl")
public class ProbInfoRepoImpl implements ProbInfoRepo {

    private final ProbInfoMapper probInfoMapper;

    @Override
    public Integer save(ProbInfo probInfo) {
        return probInfoMapper.save(probInfo);
    }

    @Override
    public Integer deleteTagInfoById(Integer proId, List<Integer> tagIds) {
        return probInfoMapper.deleteTagInfoById(proId, tagIds);
    }

    @Override
    public Integer deleteById(Number id) {
        return probInfoMapper.deleteById(id);
    }

    @Override
    public Integer updateSubmitCnt(Integer id) {
        return probInfoMapper.updateSubmitCnt(id);
    }

    @Override
    public Integer updateAcceptCnt(Integer id) {
        return probInfoMapper.updateAcceptCnt(id);
    }

    @Override
    public Integer updateById(ProbInfo probInfo) {
        return probInfoMapper.updateById(probInfo);
    }

    @Override
    public Integer insertTagInfoById(Integer proId, List<Integer> tagIds) {
        return probInfoMapper.insertTagInfoById(proId, tagIds);
    }

    @Override
    public Long queryTotal(Integer status) {
        return probInfoMapper.queryTotal(status);
    }

    @Override
    public ProbInfo getById(Number id) {
        return probInfoMapper.getById(id);
    }

    @Override
    public Collection<ProbInfo> getAll(BaseQuery query) {
        return probInfoMapper.getAll(query);
    }
}
