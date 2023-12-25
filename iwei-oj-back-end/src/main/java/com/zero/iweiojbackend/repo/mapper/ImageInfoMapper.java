package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.ImageInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * StorageImageMapper
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Mapper
public interface ImageInfoMapper {

    /**
     * 插入图片
     * @param imageInfo 图片信息
     * @return 操作成功的行数
     */
    Integer insertImage(ImageInfo imageInfo);

    /**
     * 查询图片
     * @param userId 用户 Id
     * @param imageHash 图片哈希
     * @return 图片信息
     */
    ImageInfo queryImage(Integer userId, String imageHash);
}
