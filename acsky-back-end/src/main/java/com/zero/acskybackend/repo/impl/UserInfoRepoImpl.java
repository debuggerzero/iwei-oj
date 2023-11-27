package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.query.UserInfoQuery;
import com.zero.acskybackend.model.common.Page;
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
    public Long queryTotalRecord() {
        return userInfoMapper.queryTotalRecord();
    }

    @Override
    public List<UserInfo> queryUserInfoList(Page page) {
        return userInfoMapper.queryUserInfoList(page);
    }

    @Override
    public UserInfo queryUserInfo(UserInfoQuery userInfoQuery) {
        return userInfoMapper.queryUserInfo(userInfoQuery);
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

    @Override
    public Integer deleteUserInfo(Integer id) {
        return userInfoMapper.deleteUserInfo(id);
    }
}
