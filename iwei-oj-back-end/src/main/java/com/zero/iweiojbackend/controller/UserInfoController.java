package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.user.UserInfoRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.UserRole;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 用户操作
 *
 * @author ZERO
 * @date 2023/12/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    /**
     * 账号密码登录
     *
     * @param loginRequest 账号密码
     * @return 用户信息
     */
    @PostMapping("/login/password")
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

    /**
     * 退出登录
     * @param request 请求
     * @return BaseResponse<Void>
     */
    @GetMapping("/logout")
    public BaseResponse<Void> logout(HttpServletRequest request) {
        userInfoService.logout(request);
        return ResultUtils.success(null);
    }

    /**
     * 获取登录用户
     * @param request 用户 request
     * @return BaseResponse<UserInfoVO></UserInfoVO>
     */
    @GetMapping("/query/one")
    public BaseResponse<UserInfoVO> getLoginUser(HttpServletRequest request) {
        return ResultUtils.success(userInfoService.getLoginUser(request));
    }

    /**
     * 获取用户列表（管理员）
     *
     * @return BaseResponse<GeneralCollectionResult<UserRole>>
     */
    @PostMapping("/queryUserInfoList")
    public BaseResponse<GeneralCollectionResult<UserInfo>> queryUserInfoList(@RequestBody BaseQuery baseQuery) {
        return ResultUtils.success(userInfoService.queryUserInfoList(baseQuery));
    }

    /**
     * 获取排行榜
     *
     * @param baseQuery baseQuery
     * @return BaseResponse<GeneralCollectionResult<UserRole>>
     */
    @PostMapping("/queryRanking")
    public BaseResponse<GeneralCollectionResult<UserInfoVO>> queryRanking(@RequestBody BaseQuery baseQuery) {
        return ResultUtils.success(userInfoService.queryUserInfoVOList(baseQuery));
    }

    /**
     * 获取用户角色列表（管理员）
     *
     * @return BaseResponse<GeneralCollectionResult<UserRole>>
     */
    @GetMapping("/queryUserRoleList")
    public BaseResponse<GeneralCollectionResult<UserRole>> queryUserRoleList() {
        return ResultUtils.success(userInfoService.queryUserRoleList());
    }

    /**
     * 修改用户信息
     *
     * @param userInfoRequest 用户信息交互
     * @param request request
     * @return BaseResponse<Integer>
     */
    @PutMapping("/modifyInfoByUser")
    public BaseResponse<Integer> modifyInfoByUser(@RequestBody UserInfoRequest userInfoRequest, HttpServletRequest request) {
        return ResultUtils.success(userInfoService.modifyInfoByUser(userInfoRequest, request));
    }

    /**
     * 修改用户信息（管理员）
     *
     * @param userInfoRequest 用户信息交互
     * @param request request
     * @return BaseResponse<Integer>
     */
    @PutMapping("/modifyInfoByAdmin")
    public BaseResponse<Integer> modifyInfoByAdmin(@RequestBody UserInfoRequest userInfoRequest, HttpServletRequest request) {
        return ResultUtils.success(userInfoService.modifyInfoByAdmin(userInfoRequest, request));
    }

    /**
     * 修改用户密码
     *
     * @param modifyPasswordRequest modifyPasswordCommand
     * @param request request
     * @return BaseResponse<Integer>
     */
    @PutMapping("/modifyUserPassword")
    public BaseResponse<Integer> modifyUserPassword(@RequestBody ModifyPasswordRequest modifyPasswordRequest, HttpServletRequest request) {
        return ResultUtils.success(userInfoService.modifyPasswordByUser(modifyPasswordRequest, request));
    }

    /**
     * 重置用户密码（管理员）
     *
     * @param uid 用户 id
     * @return BaseResponse<Integer>
     */
    @PutMapping("/resetPassword/{uid}")
    public BaseResponse<Integer> resetPassword(@PathVariable Integer uid) {
        return ResultUtils.success(userInfoService.resetPassword(uid));
    }

    /**
     * 添加用户信息（管理员）
     *
     * @param userInfoRequest userInfoRequest
     * @param request request
     * @return 受影响的行数
     */
    @PostMapping("/insertOneUserInfo")
    public BaseResponse<Integer> insertOneUserInfo(@RequestBody UserInfoRequest userInfoRequest, HttpServletRequest request) {
        return ResultUtils.success(userInfoService.insertOneUserInfo(userInfoRequest, request));
    }

    /**
     * 批量添加用户（管理员）
     *
     * @param file 文件
     * @return 受影响的行数
     */
    @PostMapping("/insertUserInfoList")
    public BaseResponse<Integer> insertUserInfoList(@RequestPart(value = "file") MultipartFile file, HttpServletRequest request) {
        return ResultUtils.success(userInfoService.insertUserInfoList(file, request));
    }

    /**
     * 删除用户信息（管理员）
     *
     * @param id 用户 Id
     * @return 返回受影响的行数
     */
    @DeleteMapping("/deleteUserInfo/{id}")
    public BaseResponse<Integer> deleteUserInfo(@PathVariable Integer id) {
        return ResultUtils.success(userInfoService.deleteUserInfo(id));
    }

}
