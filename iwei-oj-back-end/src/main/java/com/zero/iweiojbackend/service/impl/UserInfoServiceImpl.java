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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    public Long queryTotal(BaseQuery baseQuery) {
        return userInfoRepo.queryTotal(baseQuery);
    }

    public UserInfoVO login(LoginRequest loginRequest) {
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
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
    }

    @Override
    public void logout() {
        UserInfoVO loginUser = this.getLoginUser();
        if (Objects.isNull(loginUser)) {
            throw new AssertionException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    public UserInfoVO getLoginUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            throw new AssertionException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO((UserInfo) currentUser.getPrincipal());
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
                .peek(o -> {
                    o.setAvatar(cosService.getImageUrl(o.getAvatar()));
                    o.setPassword(null);
                })
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userInfos, this.queryTotal(query));
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
                .map(ToUserInfoVoConverter.CONVERTER::toUserInfoVO)
                .peek(o -> {
                    dataMasking(o);
                    o.setAvatar(cosService.getImageUrl(o.getAvatar()));
                })
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userInfoVos, this.queryTotal(query));
    }

    public GeneralCollectionResult<UserRole> queryUserRoleList() {
        List<UserRole> userRoles = systemRoleRepo.getAll()
                .stream()
                .map(ToUserRoleConverter.CONVERTER::toUserRole)
                .collect(Collectors.toList());
        return new GeneralCollectionResult<>(userRoles, systemRoleRepo.queryTotal());
    }

    public Integer modifyInfoByUser(UserInfoRequest userInfoRequest) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        // 判断当前登录用户与操作人 id 是否相同，不相同则无法更新
        UserInfoVO currentUserInfoVO = this.getLoginUser();
        if (!currentUserInfoVO.getId().equals(userInfoVO.getId())) {
            throw new AssertionException(ErrorCode.NO_AUTH_ERROR);
        }
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        if (ObjectUtils.isNotEmpty(userInfo.getAvatar())) {
            userInfo.setAvatar(cosService.getImageKey(userInfo.getAvatar()));
        }
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
        setUser(newUserInfo);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyInfoByAdmin(UserInfoRequest userInfoRequest) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        UserInfoVO currentUserInfoVO = this.getLoginUser();
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

    public Integer modifyPasswordByUser(ModifyPasswordRequest modifyPasswordRequest) {
        String oldPassword = modifyPasswordRequest.getOldPassword();
        String newPassword = modifyPasswordRequest.getNewPassword();
        if (Objects.isNull(oldPassword) || Objects.isNull(newPassword)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (oldPassword.equals(newPassword)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "两次密码相同");
        }
        UserInfoVO loginUser = this.getLoginUser();
        UserInfo userInfo = userInfoRepo.getOne(UserInfoQuery.builder().id(loginUser.getId()).build());
        if (userInfo.getPassword().equals(StringUtil.md5(oldPassword))) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "密码错误");
        }
        Integer result = userInfoRepo.updateUserPassword(loginUser.getId(), StringUtil.md5(newPassword));
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        this.logout();
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

    @Override
    public Integer updateSubmitCnt(Integer uid) {
        if (Objects.isNull(uid)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer result = userInfoRepo.updateSubmitCnt(uid);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    @Override
    public Integer updateAcceptCnt(Integer uid) {
        if (Objects.isNull(uid)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Integer result = userInfoRepo.updateAcceptCnt(uid);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    public Integer insertOneUserInfo(UserInfoRequest userInfoRequest) {
        if (UserInfoRequest.isNull(userInfoRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        UserInfoVO userInfoVO = userInfoRequest.getUserInfo();
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        UserInfoVO loginUser = this.getLoginUser();
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
    public Integer insertUserInfoList(MultipartFile file) {
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
                    o.setCreatePerson(this.getLoginUser().getId().toString());
                    o.setUpdatePerson(this.getLoginUser().getId().toString());
                })
                .collect(Collectors.toList());
        Integer result = userInfoRepo.saveAll(userInfos);
        if (result != userInfos.size()) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

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

    /**
     * 信息脱敏
     *
     * @param userInfoVO 用户信息
     */
    private void dataMasking(UserInfoVO userInfoVO) {
        userInfoVO.setPhone(null);
        userInfoVO.setEmail(null);
        userInfoVO.setCreateDate(null);
    }

    /**
     * 设置 shiro 用户信息
     */
    private void setUser(UserInfo userInfo) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(userInfo, realmName);
        subject.runAs(newPrincipalCollection);
    }
}