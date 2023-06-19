package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.repo.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
