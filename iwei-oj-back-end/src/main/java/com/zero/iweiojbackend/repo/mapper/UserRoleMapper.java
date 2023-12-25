package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserRoleMapper
 *
 * @author ZERO
 * @date 2023/7/2
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 添加用户角色关系
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer insertUserRole(UserInfo userInfo);

    /**
     * 批量添加用户角色关系
     * @param list 用户列表
     * @return 受影响的行数
     */
    Integer insertUserRoleList(List<UserInfo> list);

    /**
     * 修改用户角色
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer updateUserRoleById(UserInfo userInfo);

}
