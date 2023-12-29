package com.zero.iweiojbackend.model.common;

/**
 * 用户常量
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface UserConstant {
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    /**
     * 黑名单
     */
    String USER_ROLE_NONE = "none";

    /**
     * 用户
     */
    String USER_ROLE_USER = "user";

    /**
     * 管理员
     */
    String USER_ROLE_ADMIN = "admin";

    String INITIAL_PASSWORD = "123456";

}
