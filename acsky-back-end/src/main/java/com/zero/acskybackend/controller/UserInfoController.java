package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.command.InsertUserCommand;
import com.zero.acskybackend.model.command.ModifyPasswordCommand;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.common.Page;
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
     * 查询总记录
     *
     * @return 条数
     */
    @GetMapping("/query/total")
    public Long queryTotalRecord() {
        return userInfoService.queryTotalRecord();
    }

    /**
     * 获取排行榜
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/query/ranking/{pageNum}/{pageSize}")
    public List<RankingVO> queryRanking(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        return userInfoService.queryRankingList(new Page(pageNum, pageSize));
    }

    /**
     * 获取用户列表
     *
     * @return 用户信息列表
     */
    @GetMapping("/query/list/{pageNum}/{pageSize}")
    public List<UserInfoVO> queryUserInfoList(@PathVariable Long pageNum, @PathVariable Long pageSize) {
        return userInfoService.queryUserInfoList(new Page(pageNum, pageSize));
    }

    /**
     * 修改用户信息
     *
     * @param userInfoVO 用户信息可视层
     * @return 用户信息可视对象
     */
    @PutMapping("/modify/info")
    public UserInfoVO modifyInfo(@RequestBody UserInfoVO userInfoVO) {
        if (Objects.isNull(userInfoVO) || Objects.isNull(userInfoVO.getId())) {
            throw new AssertionException(GlobalExceptionEnum.INFO_UPDATE_FAIL_EXCEPTION);
        }
        if (StringUtil.isEmpty(userInfoVO.getAccount()) ||
                (!StringUtil.isEmpty(userInfoVO.getEmail()) &&
                        !StringUtil.isEmail(userInfoVO.getEmail()))) {
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
    @PutMapping("/modify/password")
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

    /**
     * 添加用户信息
     *
     * @param insertUserCommand insertUserCommand
     * @return 受影响的行数
     */
    @PostMapping("/insert/userinfo")
    public Integer insertOneUserInfo(@RequestBody InsertUserCommand insertUserCommand) {
        if (Objects.isNull(insertUserCommand)) {
            throw new AssertionException(GlobalExceptionEnum.INFO_ADD_FAIL_EXCEPTION);
        }
        if (StringUtil.isEmpty(insertUserCommand.getName()) ||
                StringUtil.isEmpty(insertUserCommand.getAccount())) {
            throw new AssertionException(GlobalExceptionEnum.INPUT_FORMAT_EXCEPTION);
        }
        return userInfoService.insertOneUserInfo(insertUserCommand);
    }

    /**
     * 批量添加用户
     *
     * @param file 文件
     * @return 受影响的行数
     */
    @PostMapping("/insert/list/userinfo")
    public Integer insertUserInfoList(@RequestPart(value = "file") MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new AssertionException(GlobalExceptionEnum.INFO_ADD_FAIL_EXCEPTION);
        }
        String xlsx = "xlsx";
        if (Objects.equals(file.getContentType(), xlsx)) {
            throw new AssertionException(GlobalExceptionEnum.FILE_TYPE_ERROR);
        }
        return userInfoService.insertUserInfoList(file);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户 Id
     * @return 返回受影响的行数
     */
    @DeleteMapping("/delete/userinfo/{id}")
    public Integer deleteUserInfoList(@PathVariable Integer id) {
        return userInfoService.deleteUserInfo(id);
    }

}
