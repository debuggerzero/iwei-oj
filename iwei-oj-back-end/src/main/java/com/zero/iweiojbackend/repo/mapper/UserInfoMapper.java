package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.query.UserInfoQuery;
import com.zero.iweiojbackend.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * UserInfoMapper
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 查询总记录
     * @return 条数
     */
    Long queryTotalRecord();

    /**
     * 获取用户信息列表
     * @param query 分页
     * @return List<UserInfo>
     */
    List<UserInfo> getAll(BaseQuery query);

    /**
     * 通过账号获取用户信息
     * @param userInfoQuery 用户信息查询器
     * @return 用户信息
     */
    UserInfo getOne(UserInfoQuery userInfoQuery);

    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer updateById(UserInfo userInfo);

    /**
     * 修改用户密码
     * @param account 账号
     * @param password 密码
     * @return 受影响的行数
     */
    Integer updateUserPassword(String account, String password);

    /**
     * 添加单个用户信息
     * @param userInfo 用户信息
     * @return 受影响的行数
     */
    Integer save(UserInfo userInfo);

    /**
     * 批量添加多个用户
     * @param list 用户信息
     * @return 受影响的行数
     */
    Integer saveAll(Collection<UserInfo> list);

    /**
     * 删除用户 id
     * @param id 用户 id
     * @return 受影响的行数
     */
    Integer deleteById(Number id);

}
