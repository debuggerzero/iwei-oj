package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.InsertUserCommand;
import com.zero.acskybackend.model.command.LoginCommand;
import com.zero.acskybackend.model.command.ModifyPasswordCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.converter.ToUserInfoConverter;
import com.zero.acskybackend.model.converter.ToUserInfoVoConverter;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.repo.mapper.UserRoleMapper;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private final PoiService poiService;

    private final UserRoleMapper userRoleMapper;

    @Resource(name = "userInfoRepoImpl")
    private final UserInfoRepo userInfoRepo;

    public Long queryTotalRecord() {
        return userInfoRepo.queryTotalRecord();
    }

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

    public List<UserInfoVO> queryUserInfoList(Page page) {
        page.setPageNumber((page.getPageNumber() - 1) * page.getPageSize());
        if (page.getPageNumber() < 0 || page.getPageSize() <= 0) {
            throw new AssertionException(GlobalExceptionEnum.INPUT_FORMAT_EXCEPTION);
        }
        return userInfoRepo.queryUserInfoList(page)
                .stream()
                .map(ToUserInfoVoConverter.CONVERTER::toUserInfoVO)
                .collect(Collectors.toList());
    }

    public UserInfoVO modifyInfo(UserInfoVO userInfoVO) {

        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        // 更新用户信息
        Integer result = userInfoRepo.updateUserInfo(userInfo);
        if (result == 0) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }

        UserInfo temp = userInfoRepo.queryUserInfo(userInfoVO.getAccount());
        temp.setAvatar(cosService.getImageUrl(temp.getAvatar()));
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(temp);
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

        Integer result = userInfoRepo.updateUserPassword(account, StringUtil.md5(newPassword));
        if (result == 0) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }
        return result;
    }

    public Integer insertOneUserInfo(InsertUserCommand insertUserCommand) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(insertUserCommand.getAccount());
        userInfo.setName(insertUserCommand.getName());
        userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        Integer userResult = userInfoRepo.insertOneUserInfo(userInfo);
        Integer roleResult = userRoleMapper.insertUserRole(userInfo);
        if (userResult == 0 || roleResult == 0) {
            throw new AssertionException(GlobalExceptionEnum.INFO_ADD_FAIL_EXCEPTION);
        }
        return userResult;
    }

    public Integer insertUserInfoList(MultipartFile file) {
        try {
            List<UserInfo> userInfoList = poiService.getUserInfoList(file.getInputStream());
            Integer userResult = userInfoRepo.insertUserInfoList(userInfoList);
            Integer roleResult = userRoleMapper.insertUserRoleList(userInfoList);
            if (userResult < userInfoList.size() || roleResult < userInfoList.size()) {
                throw new AssertionException(GlobalExceptionEnum.INFO_ADD_FAIL_EXCEPTION);
            }
            return userResult;
        } catch (IOException ex) {
            throw new AssertionException(GlobalExceptionEnum.INFO_ADD_FAIL_EXCEPTION);
        }
    }

    public Integer deleteUserInfo(Integer id) {
        Integer result = userInfoRepo.deleteUserInfo(id);
        if (result == 0) {
            throw new AssertionException(GlobalExceptionEnum.DELETE_USER_EXCEPTION);
        }
        return result;
    }
}