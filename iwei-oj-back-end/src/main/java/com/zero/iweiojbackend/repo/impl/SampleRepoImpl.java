package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.repo.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * SampleRepoImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Repository("sampleRepoImpl")
@RequiredArgsConstructor
public class SampleRepoImpl implements SampleRepo {

    private final SampleMapper sampleMapper;

    @Override
    public Integer save(Sample sample) {
        return sampleMapper.save(sample);
    }

    @Override
    public Integer saveAll(Collection<Sample> list) {
        return sampleMapper.saveAll(list);
    }

    @Override
    public Integer deleteById(Number id) {
        return sampleMapper.deleteById(id);
    }

    @Override
    public Integer deleteByIds(Collection<Number> ids) {
        return sampleMapper.deleteByIds(ids);
    }

    @Override
    public Integer updateById(Sample sample) {
        return sampleMapper.updateById(sample);
    }

    @Override
    public Integer updateByIds(Collection<Sample> list) {
        return sampleMapper.updateByIds(list);
    }

    @Override
    public Collection<Sample> getAllByProId(Number proId) {
        return sampleMapper.getAllByProId(proId);
    }
}
