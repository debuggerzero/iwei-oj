package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.SystemRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SystemRoleMapper
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Mapper
public interface SystemRoleMapper {

    /**
     * 查询系统角色
     * @param account 账号
     * @return 角色
     */
    SystemRole querySystemRole(String account);

    /**
     * 查询所有系统角色
     * @return 角色
     */
    List<SystemRole> queryAllSystemRole();
}
