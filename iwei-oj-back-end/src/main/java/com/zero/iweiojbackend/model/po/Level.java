package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户权限
 *
 * @author ZERO
 * @date 2023/7/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Level {

    /**
     * 权限 id
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

}
