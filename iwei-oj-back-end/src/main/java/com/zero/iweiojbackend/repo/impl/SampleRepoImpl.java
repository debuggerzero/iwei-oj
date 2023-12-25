package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.repo.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Sample> querySampleByProbId(Integer probId) {
        return sampleMapper.querySampleByProbId(probId);
    }
}
