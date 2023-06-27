package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.LoginCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.service.UserInfoService;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    private final UserInfoService userInfoService;

    /**
     * 账号密码登录
     * @param loginCommand 账号密码
     * @return 用户信息
     */
    @PostMapping("/password")
    public UserInfoVO login(@RequestBody LoginCommand loginCommand) {
        if (Objects.isNull(loginCommand)) {
            throw new AssertionException(GlobalExceptionEnum.ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        String account = loginCommand.getAccount();
        String password = loginCommand.getPassword();
        if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
            throw new AssertionException(GlobalExceptionEnum.ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        loginCommand.setPassword(StringUtil.md5(password));
        return userInfoService.login(loginCommand);
    }

}
