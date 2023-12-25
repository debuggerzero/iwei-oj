package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.vo.ImageInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageOperationService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface ImageOperationService {

    /**
     * 上传图片
     * @param multipartFile 图片文件
     * @param userId 用户 id
     * @return 图片信息
     */
    ImageInfoVO uploadImage(MultipartFile multipartFile, Integer userId);

}
