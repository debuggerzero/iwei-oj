package com.zero.iweiojbackend.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.iweiojbackend.model.po.Sample;
import com.zero.iweiojbackend.repo.BaseRepo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * SampleMapper
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Mapper
public interface SampleMapper extends BaseMapper<SampleMapper> {


    /**
     * 保存用例
     *
     * @param sample 用例
     * @return 受影响的行数
     */
    Integer save(@Param("sample") Sample sample);

    /**
     * 保存所有样例
     *
     * @param samples 样例集合
     * @return 受影响的行数
     */
    Integer saveAll(@Param("samples") Collection<Sample> samples);

    /**
     * 通过 id 删除样例
     * @param id 样例 id
     * @return 受影响的行数
     */
    Integer deleteById(@Param("id") Number id);

    /**
     * 删除样例集
     * @param ids id 集
     * @return 受影响的行数
     */
    Integer deleteByIds(@Param("ids") Collection<Number> ids);

    /**
     * 更新样例
     * @param sample 样例
     * @return 受影响的行数
     */
    Integer updateById(@Param("sample") Sample sample);

    /**
     * 批量更新样例
     * @param samples 预处理的集合
     * @return 受影响的行数
     */
    Integer updateByIds(@Param("samples") Collection<Sample> samples);

    /**
     * 通过题目 id 查询样例
     * @param proId 题目 id
     * @return 结果集
     */
    Collection<Sample> getAllByProId(@Param("proId") Number proId);


}
