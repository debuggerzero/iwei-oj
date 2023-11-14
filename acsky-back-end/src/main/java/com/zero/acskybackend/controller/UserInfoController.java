package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.request.InsertUserRequest;
import com.zero.acskybackend.model.request.ModifyPasswordRequest;
import com.zero.acskybackend.model.common.BaseResponse;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.utils.ResultUtils;
import com.zero.acskybackend.model.vo.RankingVO;
import com.zero.acskybackend.model.vo.UserInfoVO;
import com.zero.acskybackend.service.UserInfoService;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
     * 通过 id 查询用户信息
     * @param id 用户 id
     * @return
     */
    @GetMapping("/query/one/{userid}")
    public BaseResponse<UserInfoVO> queryUserInfoById(@PathVariable Integer userid) {
        return ResultUtils.success(userInfoService.queryUserInfoById(userid));
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
     * 获取排行榜
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/query/ranking/{pageNum}/{pageSize}")
    public BaseResponse<List<RankingVO>> queryRanking(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        if (Objects.isNull(pageNum) || Objects.isNull(pageSize) || pageNum <= 0 || pageSize <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(userInfoService.queryRankingList(new Page(pageNum, pageSize)));
    }

    /**
     * 获取用户列表
     *
     * @return 用户信息列表
     */
    @GetMapping("/query/list/{pageNum}/{pageSize}")
    public BaseResponse<List<UserInfoVO>> queryUserInfoList(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        if (Objects.isNull(pageNum) || Objects.isNull(pageSize) || pageNum <= 0 || pageSize <= 0) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(userInfoService.queryUserInfoList(new Page(pageNum, pageSize)));
    }

    /**
     * 修改用户信息
     *
     * @param userInfoVO 用户信息可视层
     * @return 用户信息可视对象
     */
    @PutMapping("/modify/info")
    public BaseResponse<UserInfoVO> modifyInfo(@RequestBody UserInfoVO userInfoVO) {
        if (Objects.isNull(userInfoVO) || Objects.isNull(userInfoVO.getId())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(userInfoVO.getAccount())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (!StringUtil.isEmpty(userInfoVO.getEmail()) && !StringUtil.isEmail(userInfoVO.getEmail())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "邮箱格式错误");
        }
        return ResultUtils.success(userInfoService.modifyInfo(userInfoVO));
    }

    /**
     * 修改用户密码
     *
     * @param modifyPasswordRequest modifyPasswordCommand
     * @return 受影响的行数
     */
    @PutMapping("/modify/password")
    public BaseResponse<Integer> modifyUserPassword(@RequestBody ModifyPasswordRequest modifyPasswordRequest) {

        if (Objects.isNull(modifyPasswordRequest)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(modifyPasswordRequest.getAccount()) ||
                StringUtil.isEmpty(modifyPasswordRequest.getOldPassword()) ||
                StringUtil.isEmpty(modifyPasswordRequest.getNewPassword())) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR, "账号密码不能为空");
        }

        return ResultUtils.success(userInfoService.modifyUserPassword(modifyPasswordRequest));
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
