package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.SystemRole;

import java.util.List;

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

    /**
     * 查询所有系统角色
     * @return 系统角色
     */
    List<SystemRole> queryAllSystemRole();
}
