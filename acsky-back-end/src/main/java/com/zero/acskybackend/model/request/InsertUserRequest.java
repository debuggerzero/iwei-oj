package com.zero.acskybackend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AddUserCommand
 *
 * @author ZERO
 * @date 2023/6/28
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
