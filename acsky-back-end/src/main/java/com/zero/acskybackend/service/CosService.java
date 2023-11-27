package com.zero.acskybackend.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.utils.IOUtils;
import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.common.ErrorCode;

import java.io.InputStream;

/**
 * CosService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface CosService {

    /**
     * 上传 Object
     * @param inputStream 数据流
     * @param key 键
     * @param contentLength 内容长度
     * @return key
     */
    String putObject(InputStream inputStream, String key, Long contentLength);

    /**
     * 获取 Object
     * @param key 键
     * @return String
     */
    String getObject(String key);

    /**
     * 删除 Object
     * @param key 键
     */
    void deleteObject(String key);

    /**
     * 获取图片链接
     * @param imageKey 图片 key
     * @return String
     */
    String getImageUrl(String imageKey);

    /**
     * 获取图片键
     * @param imageUrl 图片链接
     * @return String
     */
    String getImageKey(String imageUrl);

}
