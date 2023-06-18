package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.SystemResource;
import com.zero.acskybackend.repo.SystemResourceRepo;
import com.zero.acskybackend.repo.mapper.SystemResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SystemResourceRepoImpl
 *
 * @author ZERO
 * @date 2023/6/18
 */
@RequiredArgsConstructor
@Repository("systemResourceRepoImpl")
public class SystemResourceRepoImpl implements SystemResourceRepo {

    private final SystemResourceMapper systemResourceMapper;

    @Override
    public List<SystemResource> querySystemResourceList() {
        return systemResourceMapper.querySystemResourceList();
    }
}
