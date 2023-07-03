package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.Sample;
import com.zero.acskybackend.repo.SampleRepo;
import com.zero.acskybackend.repo.mapper.SampleMapper;
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
