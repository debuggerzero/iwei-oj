package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.po.UserInfo;

/**
 * UserInfoRepo
 *
 * @author ZERO
 * @date 2023/6/15
 */
public interface UserInfoRepo extends BaseRepo<UserInfo> {

    /**
     * 查询总记录
     * @return 条数
     */
    Long queryTotalRecord();

    /**
     * 通过账号查询用户信息
     * @param userInfoQuery 用户信息查询器
     * @return 用户信息
     */
    UserInfo getOne(UserInfoQuery userInfoQuery);

    /**
     * 修改用户密码
     * @param account 账号
     * @param password 密码
     * @return 受影响的行数
     */
    Integer updateUserPassword(String account, String password);

}
