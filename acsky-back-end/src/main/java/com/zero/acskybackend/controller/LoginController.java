package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.request.LoginRequest;
import com.zero.acskybackend.model.common.BaseResponse;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.service.UserInfoService;
import com.zero.acskybackend.utils.ResultUtils;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.service.impl.UserInfoServiceImpl;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 账号密码登录
 *
 * @author ZERO
 * @date 2023/6/19
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
