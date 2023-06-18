package com.zero.acskybackend.repo.mapper;

import com.zero.acskybackend.model.po.SystemRole;
import org.apache.ibatis.annotations.Mapper;

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
}
