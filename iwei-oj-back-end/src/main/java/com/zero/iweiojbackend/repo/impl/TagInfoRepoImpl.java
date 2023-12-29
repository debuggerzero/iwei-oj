package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.repo.TagInfoRepo;
import com.zero.iweiojbackend.repo.mapper.TagInfoMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * TagInfoRepoImpl
 *
 * @author ZERO
 * @date 2023/12/25
 */
@Repository("tagInfoRepoImpl")
public class TagInfoRepoImpl implements TagInfoRepo {

    @Resource
    private TagInfoMapper tagInfoMapper;

    @Override
    public Integer save(TagInfo tagInfo) {
        return tagInfoMapper.save(tagInfo);
    }

    @Override
    public Integer deleteById(Number id) {
        return tagInfoMapper.deleteById(id);
    }

    @Override
    public Integer updateById(TagInfo tagInfo) {
        return tagInfoMapper.updateById(tagInfo);
    }

    @Override
    public TagInfo getById(Number id) {
        return tagInfoMapper.getById(id);
    }

    @Override
    public Collection<TagInfo> getAll() {
        return tagInfoMapper.getAll();
    }

    @Override
    public Collection<TagInfo> getAll(BaseQuery query) {
        return tagInfoMapper.getAllByQuery(query);
    }

    @Override
    public Long tagCount() {
        return tagInfoMapper.tagCount();
    }

    @Override
    public Long tagRelevancyCount(Number tagId) {
        return tagInfoMapper.tagRelevancyCount(tagId);
    }
}
