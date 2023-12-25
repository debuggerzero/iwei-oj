package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.ImageInfo;
import com.zero.iweiojbackend.repo.ImageInfoRepo;
import com.zero.iweiojbackend.repo.mapper.ImageInfoMapper;
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
