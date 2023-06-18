package com.zero.acskybackend.repo;

import com.zero.acskybackend.model.po.SystemRole;

/**
 * SystemRoleRepo
 *
 * @author ZERO
 * @date 2023/6/18
 */
public interface SystemRoleRepo {

    /**
     * 查询用户角色
     * @param account 账号
     * @return 用户角色
     */
    SystemRole querySystemRole(String account);
}
