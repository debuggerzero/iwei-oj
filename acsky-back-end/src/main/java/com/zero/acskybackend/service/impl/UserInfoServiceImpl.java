package com.zero.acskybackend.service.impl;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.query.UserInfoQuery;
import com.zero.acskybackend.model.request.InsertUserRequest;
import com.zero.acskybackend.model.request.LoginRequest;
import com.zero.acskybackend.model.request.ModifyPasswordRequest;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.converter.ToRankngVOConverter;
import com.zero.acskybackend.model.converter.ToUserInfoConverter;
import com.zero.acskybackend.model.converter.ToUserInfoVoConverter;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.RankingVO;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.repo.UserInfoRepo;
import com.zero.acskybackend.repo.mapper.UserRoleMapper;
import com.zero.acskybackend.service.CosService;
import com.zero.acskybackend.service.PoiService;
import com.zero.acskybackend.service.UserInfoService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zero.acskybackend.model.common.UserConstant.USER_LOGIN_STATE;

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
        UserInfo userInfo = userInfoRepo.queryUserInfo(UserInfoQuery.builder().id(id).build());
        userInfo.setAvatar(cosService.getImageUrl(userInfo.getAvatar()));
        return ToUserInfoVoConverter.CONVERTER.toUserInfoVO(userInfo);
    }

    public List<RankingVO> queryRankingList(Page page) {
        return queryUserInfoList(page)
                .stream()
                .map(ToRankngVOConverter.CONVERTER::toRankingVO)
                .collect(Collectors.toList());
    }

    public List<UserInfoVO> queryUserInfoList(Page page) {
        page.setPageNumber((page.getPageNumber() - 1) * page.getPageSize());
        if (page.getPageNumber() < 0 || page.getPageSize() <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return userInfoRepo.queryUserInfoList(page)
                .stream()
                .map(ToUserInfoVoConverter.CONVERTER::toUserInfoVO)
                .peek(o -> o.setAvatar(cosService.getImageUrl(o.getAvatar())))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserInfoVO modifyInfo(UserInfoVO userInfoVO, HttpServletRequest request) {
        UserInfo userInfo = ToUserInfoConverter.CONVERTER.toUserInfo(userInfoVO);
        // 更新用户信息
        Integer result = userInfoRepo.updateUserInfo(userInfo);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        UserInfo temp = userInfoRepo.queryUserInfo(UserInfoQuery.builder().id(userInfo.getId()).build());
        temp.setAvatar(cosService.getImageUrl(temp.getAvatar()));
        userInfoVO = ToUserInfoVoConverter.CONVERTER.toUserInfoVO(temp);
        // 更新 session 中用户信息
        request.setAttribute(USER_LOGIN_STATE, userInfoVO);
        return userInfoVO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer modifyUserPassword(ModifyPasswordRequest modifyPasswordRequest) {

        String account = modifyPasswordRequest.getAccount();
        String oldPassword = modifyPasswordRequest.getOldPassword();
        String newPassword = modifyPasswordRequest.getNewPassword();
        if (oldPassword.equals(newPassword)) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "两次密码相同");
        }

        UserInfo userInfo = userInfoRepo.queryUserInfo(UserInfoQuery.builder().account(account).build());
        if (userInfo.getPassword().equals(StringUtil.md5(oldPassword))) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR, "密码错误");
        }

        Integer result = userInfoRepo.updateUserPassword(account, StringUtil.md5(newPassword));
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertOneUserInfo(InsertUserRequest insertUserRequest) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(insertUserRequest.getAccount());
        userInfo.setName(insertUserRequest.getName());
        userInfo.setPassword(StringUtil.md5(StringUtil.md5("123456")));
        Integer userResult = userInfoRepo.insertOneUserInfo(userInfo);
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
        Integer userResult = userInfoRepo.insertUserInfoList(userInfoList);
        Integer roleResult = userRoleMapper.insertUserRoleList(userInfoList);
        if (userResult < userInfoList.size() || roleResult < userInfoList.size()) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return userResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUserInfo(Integer id) {
        Integer result = userInfoRepo.deleteUserInfo(id);
        if (result == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return result;
    }
}