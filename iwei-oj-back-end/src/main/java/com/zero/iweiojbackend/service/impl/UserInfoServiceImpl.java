package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.dto.user.InsertUserRequest;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.model.converter.ToUserInfoConverter;
import com.zero.iweiojbackend.model.converter.ToUserInfoVoConverter;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.repo.UserInfoRepo;
import com.zero.iweiojbackend.repo.mapper.UserRoleMapper;
import com.zero.iweiojbackend.service.CosService;
import com.zero.iweiojbackend.service.PoiService;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.StringUtil;
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zero.iweiojbackend.model.common.UserConstant.USER_LOGIN_STATE;

/**
 * UserInfoServiceImpl
 *
 * @author ZERO
 * @date 2023/6/19
 */
@Service("userInfoServiceImpl")
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    @Resource(name = "cosServiceImpl")
    private CosService cosService;

    @Resource(name = "poiServiceImpl")
    private PoiService poiService;

    private final UserRoleMapper userRoleMapper;

    @Resource(name = "userInfoRepoImpl")
    private UserInfoRepo userInfoRepo;

    public Long queryTotalRecord() {
        return userInfoRepo.queryTotalRecord();
    }

    public UserInfoVO login(LoginRequest loginRequest, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getAccount(), loginRequest.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "账号密码错误");
        }
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        userInfo.setAvatar(cosService.getImageUrl(userInfo.getAvatar()));
        // 将用户信息存入 session
        UserInfoVO userInfoVO = ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
        request.getSession().setAttribute(USER_LOGIN_STATE, userInfoVO);
        return userInfoVO;
    }

    public UserInfoVO getLoginUser(HttpServletRequest request) {
        UserInfoVO currentUser = (UserInfoVO) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (Objects.isNull(currentUser) || Objects.isNull(currentUser.getId())) {
            throw new AssertionException(ErrorCode.NOT_LOGIN_ERROR);
        }
        currentUser = this.queryUserInfoById(currentUser.getId());
        if (Objects.isNull(currentUser)) {
            throw new AssertionException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    public UserInfoVO queryUserInfoById(Integer id) {
        UserInfo userInfo = userInfoRepo.getOne(UserInfoQuery.builder().id(id).build());
        userInfo.setAvatar(cosService.getImageUrl(userInfo.getAvatar()));
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
    }

    public List<UserInfoVO> queryUserInfoList(BaseQuery query) {
        Page page = query.getPage();
        page.setPageNumber((page.getPageNumber() - 1) * page.getPageSize());
        if (page.getPageNumber() < 0 || page.getPageSize() <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return userInfoRepo.getAll(query)
                .stream()
                .map(ToUserInfoVoConverter.CONVERTER::toUserInfoVO)
                .peek(o -> o.setAvatar(cosService.getImageUrl(o.getAvatar())))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserInfoVO modifyInfo(UserInfoVO userInfoVO, HttpServletRequest request) {
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        // 更新用户信息
        Integer result = userInfoRepo.updateById(userInfo);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        UserInfo temp = userInfoRepo.getOne(UserInfoQuery.builder().id(userInfo.getId()).build());
        temp.setAvatar(cosService.getImageUrl(temp.getAvatar()));
        userInfoVO = ToUserInfoVoConverter.CONVERTER.toUserInfoVO(temp);
        // 更新 session 中用户信息
        request.getSession().setAttribute(USER_LOGIN_STATE, userInfoVO);
        return userInfoVO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer modifyUserPassword(ModifyPasswordRequest modifyPasswordRequest, HttpServletRequest request) {
        String account = modifyPasswordRequest.getAccount();
        String oldPassword = modifyPasswordRequest.getOldPassword();
        String newPassword = modifyPasswordRequest.getNewPassword();
        if (oldPassword.equals(newPassword)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "两次密码相同");
        }

        UserInfo userInfo = userInfoRepo.getOne(UserInfoQuery.builder().account(account).build());
        if (userInfo.getPassword().equals(StringUtil.md5(oldPassword))) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "密码错误");
        }

        Integer result = userInfoRepo.updateUserPassword(account, StringUtil.md5(newPassword));
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertOneUserInfo(InsertUserRequest insertUserRequest) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(insertUserRequest.getAccount());
        userInfo.setName(insertUserRequest.getName());
        userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        Integer userResult = userInfoRepo.save(userInfo);
        Integer roleResult = userRoleMapper.insertUserRole(userInfo);
        if (userResult == 0 || roleResult == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return userResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertUserInfoList(MultipartFile file) {
        List<UserInfo> userInfoList;
        try {
            userInfoList = poiService.getUserInfoList(file.getInputStream());
        } catch (Exception e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        Integer userResult = userInfoRepo.saveAll(userInfoList);
        Integer roleResult = userRoleMapper.insertUserRoleList(userInfoList);
        if (userResult < userInfoList.size() || roleResult < userInfoList.size()) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return userResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserInfo(Integer id) {
        Integer result = userInfoRepo.deleteById(id);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }
}