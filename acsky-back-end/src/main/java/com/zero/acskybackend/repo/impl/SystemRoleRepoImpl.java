package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.SystemRole;
import com.zero.acskybackend.repo.SystemRoleRepo;
import com.zero.acskybackend.repo.mapper.SystemRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
