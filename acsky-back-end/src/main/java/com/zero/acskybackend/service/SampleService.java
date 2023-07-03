package com.zero.acskybackend.service;

import com.zero.acskybackend.model.po.Sample;
import com.zero.acskybackend.repo.SampleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SampleService
 *
 * @author ZERO
 * @date 2023/7/3
 */
@RequiredArgsConstructor
@Service
public class SampleService {

    @Resource(name = "sampleRepoImpl")
    private final SampleRepo sampleRepo;

    public List<Sample> querySampleByProbId(Integer probId) {
        return sampleRepo.querySampleByProbId(probId);
    }

}
