package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.vo.ImageInfoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
     * @param request request
     * @return 图片信息
     */
    ImageInfoVO uploadImage(MultipartFile multipartFile, HttpServletRequest request);

}
