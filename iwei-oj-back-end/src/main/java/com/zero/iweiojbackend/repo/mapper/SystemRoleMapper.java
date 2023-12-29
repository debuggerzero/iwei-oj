package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.SystemRole;
import com.zero.iweiojbackend.model.query.BaseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.naming.Name;
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
     * 查询总数
     * @return 总条数
     */
    Long queryTotal();

    /**
     * 查询系统角色
     * @param account 账号
     * @return 角色
     */
    SystemRole getUserRoleByAccount(@Param("account") String account);

    /**
     * 通过权限名查询用户用户角色
     * @param name 权限名
     * @return 用户角色
     */
    SystemRole getUserRoleByName(@Param("name") String name);

    /**
     * 查询所有系统角色
     * @return 角色
     */
    List<SystemRole> getAll();

}
