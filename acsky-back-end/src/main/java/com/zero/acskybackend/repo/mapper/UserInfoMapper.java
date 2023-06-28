package com.zero.acskybackend.repo.mapper;

import com.zero.acskybackend.model.po.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 添加单个用户信息
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer insertOneUserInfo(UserInfo userInfo);

    /**
     * 批量添加多个用户
     * @param list 用户信息
     * @return 受影响的行数
     */
    Integer insertUserInfoList(List<UserInfo> list);

}
