package com.zero.acskybackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 图片信息
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo {

    /**
     * 图片编号
     */
    private Integer id;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 图片哈希值
     */
    private String imageHash;

    /**
     * 创建用户
     */
    private UserInfo createUser;

    /**
     * 创建时间
     */
    private Date createDate;

}
