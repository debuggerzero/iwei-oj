package com.zero.iweiojbackend.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserInfoQuery
 *
 * @author ZERO
 * @date 2023/11/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoQuery {

    private Integer id;

    private String account;

}
