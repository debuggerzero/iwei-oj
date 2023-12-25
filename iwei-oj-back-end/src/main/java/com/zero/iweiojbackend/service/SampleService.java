package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.Sample;

import java.util.List;

/**
 * SampleService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface SampleService {

    /**
     * 通过题号查询用例
     * @param probId 题目编号
     * @return List<Sample>
     */
    List<Sample> querySampleByProbId(Integer probId);

}
