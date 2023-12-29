package com.zero.iweiojbackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户权限
 *
 * @author ZERO
 * @date 2023/12/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    /**
     * 权限 id
     */
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String describe;
}
