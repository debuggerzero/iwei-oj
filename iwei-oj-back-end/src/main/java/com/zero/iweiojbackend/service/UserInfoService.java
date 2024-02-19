package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.user.UserInfoRequest;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.model.vo.UserRole;
import org.springframework.web.multipart.MultipartFile;

/**
 * UserInfoService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface UserInfoService {

    /**
     * 查询记录条数
     *
     * @param baseQuery 状态
     * @return 条数
     */
    Long queryTotal(BaseQuery baseQuery);

    /**
     * 登录
     *
     * @param loginRequest 登录请求
     * @return UserInfoVO
     */
    UserInfoVO login(LoginRequest loginRequest);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 获取登录用户信息
     *
     * @return UserInfoVO
     */
    UserInfoVO getLoginUser();

    /**
     * 通过 id 查询用户
     *
     * @param id 用户 id
     * @return UserInfoVO
     */
    UserInfoVO queryUserInfoById(Integer id);

    /**
     * 查询用户信息列表(管理员)
     *
     * @param query 分页
     * @return 结果集
     */
    GeneralCollectionResult<UserInfo> queryUserInfoList(BaseQuery query);


    /**
     * 查询用户信息视图列表
     *
     * @param query 分页
     * @return 结果集
     */
    GeneralCollectionResult<UserInfoVO> queryUserInfoVOList(BaseQuery query);

    /**
     * 查询用户权限列表
     *
     * @return 结果集
     */
    GeneralCollectionResult<UserRole> queryUserRoleList();

    /**
     * 普通用户修改用户信息
     *
     * @param userInfoRequest 用户信息
     * @return 受影响行数
     */
    Integer modifyInfoByUser(UserInfoRequest userInfoRequest);

    /**
     * 管理员修改用户信息（管理员）
     *
     * @param userInfoRequest 用户信息
     * @return 受影响行数
     */
    Integer modifyInfoByAdmin(UserInfoRequest userInfoRequest);

    /**
     * 修改用户密码
     *
     * @param modifyPasswordRequest 用户密码请求
     * @return 操作成功的条数
     */
    Integer modifyPasswordByUser(ModifyPasswordRequest modifyPasswordRequest);

    /**
     * 重置密码(管理员)
     *
     * @param uid 用户 id
     * @return 受影响的行数
     */
    Integer resetPassword(Integer uid);

    /**
     * 更新提交次数
     *
     * @param uid 用户 id
     * @return 受影响的行数
     */
    Integer updateSubmitCnt(Integer uid);

    /**
     * 更新通过次数
     *
     * @param uid 用户 id
     * @return 受影响的行数
     */
    Integer updateAcceptCnt(Integer uid);

    /**
     * 插入一个用户 (管理员)
     *
     * @param insertUserRequest 插入用户信息请求
     * @return 操作成功的条数
     */
    Integer insertOneUserInfo(UserInfoRequest insertUserRequest);

    /**
     * 插入用户列表（管理员）
     *
     * @param file    文件
     * @return 操作成功的条数
     */
    Integer insertUserInfoList(MultipartFile file);

    /**
     * 删除用户信息（管理员）
     *
     * @param id 用户 id
     * @return 操作成功的行数
     */
    Integer deleteUserInfo(Integer id);

}
