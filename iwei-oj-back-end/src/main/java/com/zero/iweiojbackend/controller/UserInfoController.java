package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.user.InsertUserRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.common.Page;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
     * 获取登录用户
     * @param id 用户 id
     * @return
     */
    @GetMapping("/query/one")
    public BaseResponse<UserInfoVO> getLoginUser(HttpServletRequest request) {
        return ResultUtils.success(userInfoService.getLoginUser(request));
    }

    /**
     * 查询总记录
     *
     * @return 条数
     */
    @GetMapping("/query/total")
    public BaseResponse<Long> queryTotalRecord() {
        return ResultUtils.success(userInfoService.queryTotalRecord());
    }

    /**
     * 获取用户列表
     *
     * @return 用户信息列表
     */
    @PostMapping("/query/list")
    public BaseResponse<List<UserInfoVO>> queryUserInfoList(@RequestBody BaseQuery baseQuery) {
        if (Objects.isNull(baseQuery)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        Page page = baseQuery.getPage();
        if (Objects.isNull(page) || Objects.isNull(page.getPageNumber()) || Objects.isNull(page.getPageSize()) || page.getPageNumber() <= 0 || page.getPageSize() <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(userInfoService.queryUserInfoList(baseQuery));
    }

    /**
     * 获取排行榜
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/query/ranking")
    public BaseResponse<List<UserInfoVO>> queryRanking(@RequestBody BaseQuery baseQuery) {
        return this.queryUserInfoList(baseQuery);
    }

    /**
     * 修改用户信息
     *
     * @param userInfoVO 用户信息可视层
     * @param request request
     * @return 用户信息可视对象
     */
    @PutMapping("/modify/info")
    public BaseResponse<UserInfoVO> modifyInfo(@RequestBody UserInfoVO userInfoVO, HttpServletRequest request) {
        if (Objects.isNull(userInfoVO) || Objects.isNull(userInfoVO.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(userInfoVO.getAccount())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (!StringUtil.isEmpty(userInfoVO.getEmail()) && !StringUtil.isEmail(userInfoVO.getEmail())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "邮箱格式错误");
        }
        return ResultUtils.success(userInfoService.modifyInfo(userInfoVO, request));
    }

    /**
     * 修改用户密码
     *
     * @param modifyPasswordRequest modifyPasswordCommand
     * @param request request
     * @return 受影响的行数
     */
    @PutMapping("/modify/password")
    public BaseResponse<Integer> modifyUserPassword(@RequestBody ModifyPasswordRequest modifyPasswordRequest, HttpServletRequest request) {
        if (Objects.isNull(modifyPasswordRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(modifyPasswordRequest.getAccount()) ||
                StringUtil.isEmpty(modifyPasswordRequest.getOldPassword()) ||
                StringUtil.isEmpty(modifyPasswordRequest.getNewPassword())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "账号密码不能为空");
        }
        return ResultUtils.success(userInfoService.modifyUserPassword(modifyPasswordRequest, request));
    }

    /**
     * 添加用户信息
     *
     * @param insertUserRequest insertUserCommand
     * @return 受影响的行数
     */
    @PostMapping("/insert/userinfo")
    public BaseResponse<Integer> insertOneUserInfo(@RequestBody InsertUserRequest insertUserRequest) {
        if (Objects.isNull(insertUserRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(insertUserRequest.getName()) ||
                StringUtil.isEmpty(insertUserRequest.getAccount())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "账号用户名不能为空");
        }
        return ResultUtils.success(userInfoService.insertOneUserInfo(insertUserRequest));
    }

    /**
     * 批量添加用户
     *
     * @param file 文件
     * @return 受影响的行数
     */
    @PostMapping("/insert/list/userinfo")
    public BaseResponse<Integer> insertUserInfoList(@RequestPart(value = "file") MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        String xlsx = "xlsx";
        if (Objects.equals(file.getContentType(), xlsx)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "文件类型错误");
        }
        return ResultUtils.success(userInfoService.insertUserInfoList(file));
    }

    /**
     * 删除用户信息
     *
     * @param id 用户 Id
     * @return 返回受影响的行数
     */
    @DeleteMapping("/delete/userinfo/{id}")
    public BaseResponse<Integer> deleteUserInfoList(@PathVariable Integer id) {
        if (Objects.isNull(id)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(userInfoService.deleteUserInfo(id));
    }

}
