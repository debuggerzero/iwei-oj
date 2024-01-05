package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.po.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * UserInfoRepo
 *
 * @author ZERO
 * @date 2023/6/15
 */
public interface UserInfoRepo extends BaseRepo<UserInfo> {

    /**
     * 查询总记录
     *
     * @param status 状态
     * @return 条数
     */
    Long queryTotal(Number status);

    /**
     * 通过账号查询用户信息
     *
     * @param userInfoQuery 用户信息查询器
     * @return 用户信息
     */
    UserInfo getOne(UserInfoQuery userInfoQuery);

    /**
     * 修改用户密码
     *
     * @param uid      用户账号
     * @param password 密码
     * @return 受影响的行数
     */
    Integer updateUserPassword(Integer uid, String password);

    /**
     * 更新提交次数
     *
     * @param id 用户 id
     * @return 受影响的行数
     */
    Integer updateSubmitCnt(Number id);

    /**
     * 更新通过次数
     *
     * @param id 用户 id
     * @return 受影响的行数
     */
    Integer updateAcceptCnt(Number id);


    /**
     * 修改用户角色
     *
     * @param roleId 用户权限
     * @return 受影响的行数
     */
    Integer updateUserRole(Integer roleId);

}
