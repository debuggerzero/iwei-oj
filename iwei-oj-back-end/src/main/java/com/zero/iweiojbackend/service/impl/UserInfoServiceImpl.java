package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.converter.ToUserRoleConverter;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.dto.user.UserInfoRequest;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.converter.ToUserInfoConverter;
import com.zero.iweiojbackend.model.converter.ToUserInfoVoConverter;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.model.vo.UserRole;
import com.zero.iweiojbackend.repo.SystemRoleRepo;
import com.zero.iweiojbackend.repo.UserInfoRepo;
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
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zero.iweiojbackend.model.common.UserConstant.*;

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

    @Resource(name = "userInfoRepoImpl")
    private UserInfoRepo userInfoRepo;

    @Resource(name = "systemRoleRepoImpl")
    private SystemRoleRepo systemRoleRepo;

    public Long queryTotal(Integer status) {
        return userInfoRepo.queryTotal(status);
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

    public GeneralCollectionResult<UserInfo> queryUserInfoList(BaseQuery query) {
        if (BaseQuery.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Collection<UserInfo> userInfos = userInfoRepo.getAll(query)
                .stream()
                .filter(Objects::nonNull)
                .peek(o -> o.setAvatar(cosService.getImageUrl(o.getAvatar())))
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userInfos, this.queryTotal(query.getStatus()));
    }

    @Override
    public GeneralCollectionResult<UserInfoVO> queryUserInfoVOList(BaseQuery query) {
        if (BaseQuery.isNull(query)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        query.setStatus(0);
        Collection<UserInfoVO> userInfoVos = userInfoRepo.getAll(query)
                .stream()
                .filter(Objects::nonNull)
                .peek(o -> o.setAvatar(cosService.getImageUrl(o.getAvatar())))
                .map(ToUserInfoVoConverter.CONVERTER::toUserInfoVO)
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userInfoVos, this.queryTotal(0));
    }

    public GeneralCollectionResult<UserRole> queryUserRoleList() {
        List<UserRole> userRoles = systemRoleRepo.getAll()
                .stream()
                .map(ToUserRoleConverter.CONVERTER::toUserRole)
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userRoles, systemRoleRepo.queryTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer modifyInfoByUser(UserInfoRequest userInfoRequest, HttpServletRequest request) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        // 判断当前登录用户与操作人 id 是否相同，不相同则无法更新
        UserInfoVO currentUserInfoVO = this.getLoginUser(request);
        if (currentUserInfoVO.getId().equals(userInfoVO.getId())) {
            throw new AssertionException(ErrorCode.NO_AUTH_ERROR);
        }
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        userInfo.setAvatar(cosService.getImageKey(userInfo.getAvatar()));
        userInfo.setUpdatePerson(currentUserInfoVO.getId().toString());
        // 更新用户信息
        Integer result = userInfoRepo.updateById(userInfo);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        UserInfo newUserInfo = userInfoRepo.getOne(UserInfoQuery.builder().id(userInfo.getId()).build());
        if (Objects.isNull(newUserInfo)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        newUserInfo.setAvatar(cosService.getImageUrl(newUserInfo.getAvatar()));
        // 更新 session 中用户信息
        request.getSession().setAttribute(USER_LOGIN_STATE, ToUserInfoVoConverter.CONVERTER.toUserInfoVO(newUserInfo));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyInfoByAdmin(UserInfoRequest userInfoRequest, HttpServletRequest request) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        UserInfoVO currentUserInfoVO = this.getLoginUser(request);
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        userInfo.setStatus(userInfoRequest.getStatus());
        userInfo.setAvatar(cosService.getImageKey(userInfo.getAvatar()));
        userInfo.setUpdatePerson(currentUserInfoVO.getId().toString());
        // 更新用户信息
        Integer result = userInfoRepo.updateById(userInfo);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        // 更新用户权限
        UserRole role = userInfoVO.getRole();
        if (Objects.isNull(role) || Objects.isNull(role.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer i = userInfoRepo.updateUserRole(role.getId());
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer modifyPasswordByUser(ModifyPasswordRequest modifyPasswordRequest, HttpServletRequest request) {
        String oldPassword = modifyPasswordRequest.getOldPassword();
        String newPassword = modifyPasswordRequest.getNewPassword();
        if (Objects.isNull(oldPassword) || Objects.isNull(newPassword)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (oldPassword.equals(newPassword)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "两次密码相同");
        }
        UserInfoVO loginUser = this.getLoginUser(request);
        UserInfo userInfo = userInfoRepo.getOne(UserInfoQuery.builder().id(loginUser.getId()).build());
        if (userInfo.getPassword().equals(StringUtil.md5(oldPassword))) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "密码错误");
        }
        Integer result = userInfoRepo.updateUserPassword(loginUser.getId(), StringUtil.md5(newPassword));
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return result;
    }

    @Override
    public Integer resetPassword(Integer uid) {
        if (Objects.isNull(uid)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer i = userInfoRepo.updateUserPassword(uid, StringUtil.md5(StringUtil.md5(INITIAL_PASSWORD)));
        if (i == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertOneUserInfo(UserInfoRequest userInfoRequest, HttpServletRequest request) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        UserInfoVO loginUser = this.getLoginUser(request);
        if (Objects.isNull(userInfo.getRole()) || Objects.isNull(userInfo.getRole().getId())) {
            userInfo.setRole(systemRoleRepo.getUserRoleByName(USER_ROLE_USER));
        }
        userInfo.setPassword(StringUtil.md5(StringUtil.md5(INITIAL_PASSWORD)));
        userInfo.setCreatePerson(loginUser.getId().toString());
        userInfo.setUpdatePerson(loginUser.getId().toString());
        Integer result = userInfoRepo.save(userInfo);
        if (result == 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertUserInfoList(MultipartFile file, HttpServletRequest request) {
        if (Objects.isNull(file)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        // 判断文件格式已否正确
        String xlsx = "xlsx";
        if (Objects.equals(file.getContentType(), xlsx)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "文件类型错误");
        }
        // 处理表格
        List<UserInfo> userInfoList;
        try {
            userInfoList = poiService.getUserInfoList(file.getInputStream());
        } catch (Exception e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        // 处理 userinfo
        Collection<UserInfo> userInfos = userInfoList
                .stream()
                .peek(o -> {
                    o.setPassword(StringUtil.md5(StringUtil.md5(INITIAL_PASSWORD)));
                    o.setRole(systemRoleRepo.getUserRoleByName(USER_ROLE_USER));
                    o.setCreatePerson(this.getLoginUser(request).getId().toString());
                    o.setUpdatePerson(this.getLoginUser(request).getId().toString());
                })
                .collect(Collectors.toList());
        Integer result = userInfoRepo.saveAll(userInfos);
        if (result != userInfos.size()) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserInfo(Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer result = userInfoRepo.deleteById(id);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }
}