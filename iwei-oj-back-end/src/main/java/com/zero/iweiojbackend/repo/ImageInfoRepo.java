package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.ImageInfo;

/**
 * ImageInfoRepo
 *
 * @author ZERO
 * @date 2023/6/20
 */
public interface ImageInfoRepo {

    /**
     * 插入图片
     * @param imageInfo 图片信息
     * @return 返回操作成功的行数
     */
    Integer insertImage(ImageInfo imageInfo);

    /**
     * 通过图片哈希值查询图片信息
     * @param userId 用户 Id
     * @param imageHash 图片哈希值
     * @return 图片信息
     */
    ImageInfo queryImageInfoByHash(Integer userId, String imageHash);

}
