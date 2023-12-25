package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 账号密码登录
 *
 * @author ZERO
 * @date 2023/12/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    /**
     * 账号密码登录
     * @param loginRequest 账号密码
     * @return 用户信息
     */
    @PostMapping("/password")
    public BaseResponse<UserInfoVO> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        if (Objects.isNull(loginRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        if (StringUtil.isEmpty(account)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR.getCode(), "账号不能为空");
        }
        if (StringUtil.isEmpty(password)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR.getCode(), "密码不能为空");
        }
        loginRequest.setPassword(StringUtil.md5(password));
        return ResultUtils.success(userInfoService.login(loginRequest, request));
    }

}
