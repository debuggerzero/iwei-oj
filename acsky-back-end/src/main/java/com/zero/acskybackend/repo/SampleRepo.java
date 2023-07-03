package com.zero.acskybackend.repo;

import com.zero.acskybackend.model.po.Sample;

import java.util.List;

/**
 * SampleRepo
 *
 * @author ZERO
 * @date 2023/7/3
 */
public interface SampleRepo {

    /**
     * 通过题号查询用例
     * @param probId 题号
     * @return 用例信息
     */
    List<Sample> querySampleByProbId(Integer probId);

}
