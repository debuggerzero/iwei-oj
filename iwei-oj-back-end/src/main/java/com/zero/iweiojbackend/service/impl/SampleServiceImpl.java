package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.repo.SampleRepo;
import com.zero.iweiojbackend.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SampleServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("sampleServiceImpl")
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    @Resource(name = "sampleRepoImpl")
    private final SampleRepo sampleRepo;

    public List<Sample> querySampleByProbId(Integer probId) {
        return sampleRepo.querySampleByProbId(probId);
    }

}
