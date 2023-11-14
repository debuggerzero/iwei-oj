package com.zero.acskybackend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginCommand
 *
 * @author ZERO
 * @date 2023/6/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String account;

    private String password;

}
