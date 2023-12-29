package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.Sample;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * SampleRepo
 *
 * @author ZERO
 * @date 2023/7/3
 */
public interface SampleRepo extends BaseRepo<Sample> {

    /**
     * 通过题目 id 查询样例
     * @param proId 题目 id
     * @return 结果集
     */
    Collection<Sample> getAllByProId(Number proId);

}
