package com.zero.iweiojbackend.controller;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.BaseResponse;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.service.ImageOperationService;
import com.zero.iweiojbackend.utils.ResultUtils;
import com.zero.iweiojbackend.model.vo.ImageInfoVO;
import com.zero.iweiojbackend.service.impl.ImageOperationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    @Resource(name = "imageOperationServiceImpl")
    private ImageOperationService fileOperationService;

    /**
     * 上传图片
     * @param file 图片
     * @param userId 用户 Id
     * @return 图片信息
     */
    @PostMapping("/upload/image/{userId}")
    public BaseResponse<ImageInfoVO> uploadImage(@RequestPart(value = "file") MultipartFile file, @PathVariable Integer userId) {
        if (Objects.isNull(file)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR.getCode(), "文件上传失败");
        }
        if (Objects.isNull(userId)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(fileOperationService.uploadImage(file, userId));
    }
}
