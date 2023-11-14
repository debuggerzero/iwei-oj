package com.zero.acskybackend.model.Query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
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
