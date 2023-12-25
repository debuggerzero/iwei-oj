package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.dto.user.InsertUserRequest;
import com.zero.iweiojbackend.model.dto.user.LoginRequest;
import com.zero.iweiojbackend.model.dto.user.ModifyPasswordRequest;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserInfoService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface UserInfoService {

    /**
     * 查询记录条数
     * @return 条数
     */
    Long queryTotalRecord();

    /**
     * 登录
     * @param loginRequest 登录请求
     * @param request 请求
     * @return UserInfoVO
     */
    UserInfoVO login(LoginRequest loginRequest, HttpServletRequest request);

    /**
     * 获取登录用户信息
     * @param request 请求信息
     * @return UserInfoVO
     */
    UserInfoVO getLoginUser(HttpServletRequest request);

    /**
     * 通过 id 查询用户
     * @param id 用户 id
     * @return UserInfoVO
     */
    UserInfoVO queryUserInfoById(Integer id);

    /**
     * 查询用户信息列表(管理员)
     * @param query 分页
     * @return List<UserInfoVO>
     */
    List<UserInfoVO> queryUserInfoList(BaseQuery query);

    /**
     * 修改用户信息
     * @param userInfoVO 用户信息
     * @param request 请求
     * @return UserInfoVO
     */
    UserInfoVO modifyInfo(UserInfoVO userInfoVO, HttpServletRequest request);

    /**
     * 修改用户密码
     * @param modifyPasswordRequest 用户密码请求
     * @return 操作成功的条数
     */
    Integer modifyUserPassword(ModifyPasswordRequest modifyPasswordRequest, HttpServletRequest request);

    /**
     * 插入一个用户 (管理员)
     * @param insertUserRequest 插入用户信息请求
     * @return 操作成功的条数
     */
    Integer insertOneUserInfo(InsertUserRequest insertUserRequest);

    /**
     * 插入用户列表
     * @param file 文件
     * @return 操作成功的条数
     */
    Integer insertUserInfoList(MultipartFile file);

    /**
     * 删除用户信息
     * @param id 用户 id
     * @return 操作成功的行数
     */
    Integer deleteUserInfo(Integer id);

}
