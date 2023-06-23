package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.LoginCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.converter.ToUserInfoVoConverter;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * UserInfoService
 *
 * @author ZERO
 * @date 2023/6/19
 */
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final CosService cosService;

    public UserInfoVO login(LoginCommand loginCommand) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginCommand.getAccount(), loginCommand.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException|IncorrectCredentialsException e) {
            throw new AssertionException(GlobalExceptionEnum.ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        userInfo.setAvatar(cosService.getImageUrl(userInfo.getAvatar()));
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
    }
}
