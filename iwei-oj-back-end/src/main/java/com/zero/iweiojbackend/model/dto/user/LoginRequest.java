package com.zero.iweiojbackend.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginRequest
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String account;

    private String password;

}
