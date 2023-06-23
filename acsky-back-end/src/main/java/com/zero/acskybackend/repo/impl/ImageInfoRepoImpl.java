package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.ImageInfo;
import com.zero.acskybackend.repo.ImageInfoRepo;
import com.zero.acskybackend.repo.mapper.ImageInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * ImageInfoRepoImpl
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Repository("imageInfoRepoImpl")
@RequiredArgsConstructor
public class ImageInfoRepoImpl implements ImageInfoRepo {

    private final ImageInfoMapper imageInfoMapper;

    @Override
    public Integer insertImage(ImageInfo imageInfo) {
        return imageInfoMapper.insertImage(imageInfo);
    }

    @Override
    public ImageInfo queryImageInfoByHash(Integer userId, String imageHash) {
        return imageInfoMapper.queryImage(userId, imageHash);
    }

}
