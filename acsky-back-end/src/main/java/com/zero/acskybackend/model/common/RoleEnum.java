package com.zero.acskybackend.model.common;

import lombok.Getter;

/**
 * 角色枚举
 *
 * @author ZERO
 * @date 2023/6/30
 */
@Getter
public enum RoleEnum {

    /**
     * 匿名用户
     */
    NONE(0, "匿名用户"),

    /**
     * 普通用户
     */
    COMMON(1, "普通用户"),

    /**
     * 管理员
     */
    ADMIN(2, "管理员");

    RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 角色 Id
     */
    private final Integer id;

    /**
     * 角色名
     */
    private final String name;
}
