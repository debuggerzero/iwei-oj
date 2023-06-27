package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.LoginCommand;
import com.zero.acskybackend.model.command.ModifyPasswordCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.converter.ToUserInfoVoConverter;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * UserInfoService
 *
 * @author ZERO
 * @date 2023/6/19
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserInfoService {

    private final CosService cosService;

    @Resource(name = "userInfoRepoImpl")
    private final UserInfoRepo userInfoRepo;

    public UserInfoVO login(LoginCommand loginCommand) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginCommand.getAccount(), loginCommand.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            throw new AssertionException(GlobalExceptionEnum.ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        userInfo.setAvatar(cosService.getImageUrl(userInfo.getAvatar()));
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
    }

    public UserInfoVO modifyInfo(UserInfoVO userInfoVO) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoVO.getId());
        userInfo.setName(userInfoVO.getName());
        userInfo.setEmail(userInfoVO.getEmail());
        userInfo.setPhone(userInfoVO.getPhone());
        userInfo.setProfile(userInfoVO.getProfile());
        userInfo.setAvatar(cosService.getImageKey(userInfoVO.getAvatar()));

        // 更新用户信息
        Integer result = userInfoRepo.updateUserInfo(userInfo);
        if (result == 0) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }

        return userInfoVO;
    }

    public Integer modifyUserPassword(ModifyPasswordCommand modifyPasswordCommand) {

        String account = modifyPasswordCommand.getAccount();
        String oldPassword = modifyPasswordCommand.getOldPassword();
        String newPassword = modifyPasswordCommand.getNewPassword();

        UserInfo userInfo = userInfoRepo.queryUserInfo(account);
        if (userInfo.getPassword().equals(StringUtil.md5(oldPassword))) {
            throw new AssertionException(GlobalExceptionEnum.PASSWORD_INPUT_EXCEPTION);
        }
        if (oldPassword.equals(newPassword)) {
            throw new AssertionException(GlobalExceptionEnum.OLD_NEW_PASSWORD_SAME_EXCEPTION);
        }

        return userInfoRepo.updateUserPassword(account, StringUtil.md5(newPassword));
    }
}