package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.SystemRole;

import java.util.List;

/**
 * SystemRoleRepo
 *
 * @author ZERO
 * @date 2023/6/18
 */
public interface SystemRoleRepo extends BaseRepo<SystemRole> {

    /**
     * 查询总数
     * @return 总条数
     */
    Long queryTotal();


    /**
     * 查询用户角色
     * @param account 账号
     * @return 用户角色
     */
    SystemRole getUserRoleByAccount(String account);

    /**
     * 通过权限名查询用户用户角色
     * @param name 权限名
     * @return 用户角色
     */
    SystemRole getUserRoleByName(String name);

}
