package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * TagInfoMapper
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Mapper
public interface TagInfoMapper {

    /**
     * 标签总数
     * @return 结果
     */
    Long tagCount();

    /**
     * 标签关联总数
     * @param tagId tagId 标签 id
     * @return 结果
     */
    Long tagRelevancyCount(@Param("tagId") Number tagId);

    /**
     * 添加标签信息
     * @param tagInfo 标签信息
     * @return 受影响的行数
     */
    Integer save(TagInfo tagInfo);

    /**
     * 删除标签信息
     * @param id 标签 id
     * @return 受影响的行数
     */
    Integer deleteById(@Param("id") Number id);

    /**
     * 更新标签信息
     * @param tagInfo 标签信息
     * @return 受影响的行数
     */
    Integer updateById(TagInfo tagInfo);

    /**
     * 查询所有标签
     * @return 查询所有标签
     */
    Collection<TagInfo> getAll();

    /**
     * 查询所有标签信息
     * @param baseQuery 基础查询
     * @return 结果集
     */
    Collection<TagInfo> getAllByQuery(BaseQuery baseQuery);

    /**
     * 通过 id 查询标签信息
     * @param id 标签信息
     * @return 结果
     */
    TagInfo getById(@Param("id") Number id);
}
