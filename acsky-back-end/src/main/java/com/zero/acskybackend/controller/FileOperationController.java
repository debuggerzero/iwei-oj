package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.vo.ImageInfoVO;
import com.zero.acskybackend.service.ImageOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

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

    private final ImageOperationService fileOperationService;

    /**
     * 上传图片
     * @param file 图片
     * @param id 用户 Id
     * @return 图片信息
     */
    @PostMapping("/upload/image/{id}")
    public ImageInfoVO uploadImage(@RequestPart(value = "file") MultipartFile file, @PathVariable Integer id) {

        if (Objects.isNull(file)) {
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
        }

        return fileOperationService.uploadImage(file, id);
    }
}
