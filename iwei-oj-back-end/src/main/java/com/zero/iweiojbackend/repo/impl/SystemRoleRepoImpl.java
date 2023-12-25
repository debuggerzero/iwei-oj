package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.SystemRole;
import com.zero.iweiojbackend.repo.SystemRoleRepo;
import com.zero.iweiojbackend.repo.mapper.SystemRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public SystemRole querySystemRole(String account) {
        return systemRoleMapper.querySystemRole(account);
    }

    @Override
    public List<SystemRole> queryAllSystemRole() {
        return systemRoleMapper.queryAllSystemRole();
    }
}
