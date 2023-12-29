package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.SystemRole;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.repo.SystemRoleRepo;
import com.zero.iweiojbackend.repo.mapper.SystemRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * SystemRoleRepoImpl
 *
 * @author ZERO
 * @date 2023/6/18
 */
@RequiredArgsConstructor
@Repository("systemRoleRepoImpl")
public class SystemRoleRepoImpl implements SystemRoleRepo {

    private final SystemRoleMapper systemRoleMapper;

    @Override
    public Long queryTotal() {
        return systemRoleMapper.queryTotal();
    }

    @Override
    public SystemRole getUserRoleByAccount(String account) {
        return systemRoleMapper.getUserRoleByAccount(account);
    }

    @Override
    public SystemRole getUserRoleByName(String name) {
        return systemRoleMapper.getUserRoleByName(name);
    }

    @Override
    public Collection<SystemRole> getAll() {
        return systemRoleMapper.getAll();
    }
}
