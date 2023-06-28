package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.repo.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInfoRepoImpl
 *
 * @author ZERO
 * @date 2023/6/15
 */
@RequiredArgsConstructor
@Repository("userInfoRepoImpl")
public class UserInfoRepoImpl implements UserInfoRepo {

    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo queryUserInfo(String account) {
        return userInfoMapper.queryUserInfo(account);
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public Integer updateUserPassword(String account, String password) {
        return userInfoMapper.updateUserPassword(account, password);
    }

    @Override
    public Integer insertOneUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertOneUserInfo(userInfo);
    }

    @Override
    public Integer insertUserInfoList(List<UserInfo> list) {
        return userInfoMapper.insertUserInfoList(list);
    }
}
