package com.zero.acskybackend.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ModifyPasswordCommand
 *
 * @author ZERO
 * @date 2023/6/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswordCommand {

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
