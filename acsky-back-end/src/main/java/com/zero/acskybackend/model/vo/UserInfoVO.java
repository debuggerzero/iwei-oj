package com.zero.acskybackend.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero.acskybackend.model.po.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * UserInfoVO
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 权限
     */
    private Level level;

}
