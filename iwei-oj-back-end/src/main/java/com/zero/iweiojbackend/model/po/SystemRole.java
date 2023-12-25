package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统角色
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemRole {

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String describe;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建用户
     */
    private String createPerson;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 修改用户
     */
    private String updatePerson;

    /**
     * 描述
     */
    private String remark;

}
