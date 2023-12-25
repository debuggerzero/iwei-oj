package com.zero.iweiojbackend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * InsertUserRequest
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertUserRequest {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名
     */
    private String name;

}
