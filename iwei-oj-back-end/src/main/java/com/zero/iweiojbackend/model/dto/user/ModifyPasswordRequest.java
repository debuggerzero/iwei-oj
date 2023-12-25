package com.zero.iweiojbackend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ModifyPasswordCommand
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswordRequest {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 旧的密码
     */
    private String oldPassword;

    /**
     * 新的密码
     */
    private String newPassword;

}
