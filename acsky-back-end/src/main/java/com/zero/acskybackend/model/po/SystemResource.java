package com.zero.acskybackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统资源
 *
 * @author ZERO
 * @date 2023/6/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemResource {

    /**
     * id
     */
    private Integer id;

    /**
     * 资源名
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 资源标识
     */
    private String identity;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

}
