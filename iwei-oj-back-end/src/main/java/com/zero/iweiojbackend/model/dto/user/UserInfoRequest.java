package com.zero.iweiojbackend.model.dto.user;

import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * InsertUserRequest
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {

    /**
     * 用户信息
     */
    private UserInfoVO userInfo;

    /**
     * 状态
     */
    private Integer status;

    public static boolean isNull(UserInfoRequest userInfoRequest) {
        if (Objects.isNull(userInfoRequest)) {
            return true;
        }
        UserInfoVO userInfo = userInfoRequest.userInfo;
        if (Objects.isNull(userInfo) || Objects.isNull(userInfo.getId())) {
            return true;
        }
        return !StringUtil.isEmpty(userInfo.getEmail()) && !StringUtil.isEmail(userInfo.getEmail());
    }

}
