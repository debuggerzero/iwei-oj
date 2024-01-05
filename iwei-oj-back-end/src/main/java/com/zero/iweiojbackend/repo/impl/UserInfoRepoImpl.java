package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.repo.UserInfoRepo;
import com.zero.iweiojbackend.repo.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
    public Long queryTotal(BaseQuery baseQuery) {
        return userInfoMapper.queryTotal(baseQuery);
    }

    @Override
    public List<UserInfo> getAll(BaseQuery query) {
        return userInfoMapper.getAll(query);
    }

    @Override
    public UserInfo getOne(UserInfoQuery userInfoQuery) {
        return userInfoMapper.getOne(userInfoQuery);
    }

    @Override
    public Integer updateById(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }

    @Override
    public Integer updateUserPassword(Integer uid, String password) {
        return userInfoMapper.updateUserPassword(uid, password);
    }

    @Override
    public Integer updateSubmitCnt(Number id) {
        return userInfoMapper.updateSubmitCnt(id);
    }

    @Override
    public Integer updateAcceptCnt(Number id) {
        return userInfoMapper.updateAcceptCnt(id);
    }

    @Override
    public Integer updateUserRole(Integer roleId) {
        return userInfoMapper.updateUserRole(roleId);
    }

    @Override
    public Integer save(UserInfo userInfo) {
        return userInfoMapper.save(userInfo);
    }

    @Override
    public Integer saveAll(Collection<UserInfo> list) {
        return userInfoMapper.saveAll(list);
    }

    @Override
    public Integer deleteById(Number id) {
        return userInfoMapper.deleteById(id);
    }
}
