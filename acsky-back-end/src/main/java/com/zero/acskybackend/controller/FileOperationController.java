package com.zero.acskybackend.controller;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.common.BaseResponse;
import com.zero.acskybackend.model.common.ErrorCode;
import com.zero.acskybackend.utils.ResultUtils;
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
