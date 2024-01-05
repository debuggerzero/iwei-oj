package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.po.ProbInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * ProbInfoMapper
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Mapper
public interface ProbInfoMapper {

    /**
     * 查询题目总数
     * @param baseQuery 查询器
     * @return 题目总数
     */
    Long queryTotal(BaseQuery baseQuery);

    /**
     * 查询所有题目
     * @param query 分页器
     * @return 结果集
     */
    Collection<ProbInfo> getAll(BaseQuery query);

    /**
     * 查询一个题目
     * @param id 题目 id
     * @return 返回题目信息
     */
    ProbInfo getById(@Param("id") Number id);

    /**
     * 添加题目
     * @param probInfo 题目信息
     * @return 受影响的行数
     */
    Integer save(ProbInfo probInfo);

    /**
     * 更新提交次数
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer updateSubmitCnt(@Param("id") Number id);

    /**
     * 更新通过次数
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer updateAcceptCnt(@Param("id") Number id);

    /**
     * 通过 id 修改题目信息
     * @param probInfo i题目信息
     * @return 受影响的行数
     */
    Integer updateById(ProbInfo probInfo);

    /**
     * 更新 tagInfo
     * @param proId 题目 id
     * @param tagIds 标签 ids
     * @return 受影响的行数
     */
    Integer insertTagInfoById(@Param("proId") Number proId, @Param("tagIds") List<Integer> tagIds);

    /**
     * 删除 pro_tag 关联信息
     * @param proId 题目 id
     * @param tagIds 标签 id
     * @return 受影响的行数
     */
    Integer deleteTagInfoById(@Param("proId") Number proId, @Param("tagIds") List<Integer> tagIds);

    /**
     * 通过 id 删除题目信息
     * @param id 题目信息
     * @return 受影响的行数
     */
    Integer deleteById(@Param("id") Number id);

}
