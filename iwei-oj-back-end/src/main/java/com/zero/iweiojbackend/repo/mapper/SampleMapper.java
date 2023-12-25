package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.Sample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SampleMapper
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Mapper
public interface SampleMapper {

    /**
     * 通过题号查询用例
     * @param probId 题号
     * @return 用例列表
     */
    List<Sample> querySampleByProbId(Integer probId);

}
