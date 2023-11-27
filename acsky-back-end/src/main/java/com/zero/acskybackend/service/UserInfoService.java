package com.zero.acskybackend.service;

import com.zero.acskybackend.model.common.Page;
import com.zero.acskybackend.model.request.InsertUserRequest;
import com.zero.acskybackend.model.request.LoginRequest;
import com.zero.acskybackend.model.request.ModifyPasswordRequest;
import com.zero.acskybackend.model.vo.RankingVO;
import com.zero.acskybackend.model.vo.UserInfoVO;
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
     * 获取排行榜
     * @param page 分页
     * @return List<RankingVO>
     */
    List<RankingVO> queryRankingList(Page page);

    /**
     * 查询用户信息列表(管理员)
     * @param page 分页
     * @return List<UserInfoVO>
     */
    List<UserInfoVO> queryUserInfoList(Page page);

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
    Integer modifyUserPassword(ModifyPasswordRequest modifyPasswordRequest);

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
