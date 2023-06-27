package com.zero.acskybackend.repo.mapper;

import com.zero.acskybackend.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserInfoMapper
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 通过账号获取用户信息
     * @param account 账号
     * @return 用户信息
     */
    UserInfo queryUserInfo(String account);

    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer updateUserInfo(UserInfo userInfo);

    /**
     * 修改用户密码
     * @param account 账号
     * @param password 密码
     * @return 受影响的行数
     */
    Integer updateUserPassword(String account, String password);

}
