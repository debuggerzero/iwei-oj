package com.zero.acskybackend.repo;

import com.zero.acskybackend.model.po.UserInfo;

/**
 * UserInfoRepo
 *
 * @author ZERO
 * @date 2023/6/15
 */
public interface UserInfoRepo {

    /**
     * 通过账号查询用户信息
     * @param account 账号
     * @return 用户信息
     */
    UserInfo queryUserInfo(String account);

}
