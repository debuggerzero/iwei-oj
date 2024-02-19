package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.service.ImageOperationService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.ImageInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传
 *
 * @author ZERO
 * @date 2023/6/23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileOperationController {

    @Resource(name = "imageOperationServiceImpl")
    private ImageOperationService fileOperationService;

    /**
     * 上传图片
     * @param file 图片
     * @return 图片信息
     */
    @PostMapping("/upload/image")
    public BaseResponse<ImageInfoVO> uploadImage(@RequestPart(value = "file") MultipartFile file) {
        return ResultUtils.success(fileOperationService.uploadImage(file));
    }
}
