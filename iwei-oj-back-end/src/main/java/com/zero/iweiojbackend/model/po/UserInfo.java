package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息 PO
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    /**
     * 用户 ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 用户权限
     */
    private Level level;

    /**
     * 提交数
     */
    private Long submitCnt;

    /**
     * 通过数
     */
    private Long acceptCnt;

}
