package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.ModifyPasswordCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.service.UserInfoService;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户操作
 *
 * @author ZERO
 * @date 2023/6/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * 修改用户信息
     *
     * @param userInfoVO 用户信息可视层
     * @return 用户信息可视对象
     */
    @PostMapping("/modify/info")
    public UserInfoVO modifyInfo(@RequestBody UserInfoVO userInfoVO) {
        if (Objects.isNull(userInfoVO) || Objects.isNull(userInfoVO.getId())) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }
        if (StringUtil.isEmpty(userInfoVO.getAccount()) ||
                StringUtil.isEmpty(userInfoVO.getName()) ||
                StringUtil.isEmpty(userInfoVO.getEmail()) ||
                StringUtil.isEmpty(userInfoVO.getPhone()) ||
                StringUtil.isEmpty(userInfoVO.getProfile()) ||
                StringUtil.isEmpty(userInfoVO.getAvatar()) ||
                !StringUtil.isEmail(userInfoVO.getEmail())) {
            throw new AssertionException(GlobalExceptionEnum.INPUT_FORMAT_EXCEPTION);
        }
        return userInfoService.modifyInfo(userInfoVO);
    }

    /**
     * 修改用户密码
     *
     * @param modifyPasswordCommand modifyPasswordCommand
     * @return 受影响的行数
     */
    @PostMapping("/modify/password")
    public Integer modifyUserPassword(@RequestBody ModifyPasswordCommand modifyPasswordCommand) {

        if (Objects.isNull(modifyPasswordCommand)) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }
        if (StringUtil.isEmpty(modifyPasswordCommand.getAccount()) ||
                StringUtil.isEmpty(modifyPasswordCommand.getOldPassword()) ||
                StringUtil.isEmpty(modifyPasswordCommand.getNewPassword())) {
            throw new AssertionException(GlobalExceptionEnum.INPUT_FORMAT_EXCEPTION);
        }

        return userInfoService.modifyUserPassword(modifyPasswordCommand);
    }

}
