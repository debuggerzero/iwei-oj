package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.model.po.ImageInfo;
import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.ImageInfoVO;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import com.zero.iweiojbackend.repo.ImageInfoRepo;
import com.zero.iweiojbackend.service.CosService;
import com.zero.iweiojbackend.service.ImageOperationService;
import com.zero.iweiojbackend.service.UserInfoService;
import com.zero.iweiojbackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * ImageOperationServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("imageOperationServiceImpl")
@RequiredArgsConstructor
public class ImageOperationServiceImpl implements ImageOperationService {

    @Resource(name = "cosServiceImpl")
    private CosService cosService;

    @Resource(name = "imageInfoRepoImpl")
    private ImageInfoRepo imageInfoRepo;

    @Resource(name = "userInfoServiceImpl")
    private UserInfoService userInfoService;

    private String getFilename(String contentType) {
        if (Objects.isNull(contentType)) {
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        return StringUtil.uuid() + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
    }

    private String getImageHash(InputStream inputStream) {
        String hash;
        try {
            hash = DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            throw new AssertionException(ErrorCode.SYSTEM_ERROR);
        }
        return hash;
    }

    private ImageInfoVO insertImage(
            Integer userId,
            String name,
            String imageHash,
            InputStream inputStream,
            long contentLength
    ) {
        String key = "/" + userId + "/assets/" + name;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        Integer length = imageInfoRepo.insertImage(new ImageInfo(null, name, key, imageHash, userInfo, null));
        if (length == 0) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        key = cosService.putObject(inputStream, key, contentLength);
        return new ImageInfoVO(-1, name, cosService.getImageUrl(key));
    }

    private ByteArrayOutputStream cloneInputStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            int res;
            while ((res = inputStream.read()) != -1) {
                outputStream.write(res);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        return outputStream;
    }

    @Transactional(rollbackFor = Exception.class)
    public ImageInfoVO uploadImage(MultipartFile multipartFile, HttpServletRequest request) {
        if (Objects.isNull(multipartFile)) {
            throw new AssertionException(ErrorCode.PARAMS_ERROR.getCode(), "文件上传失败");
        }
        UserInfoVO loginUser = userInfoService.getLoginUser(request);
        Integer userId = loginUser.getId();
        InputStream inputStream;
        long contentLength;
        try {
            inputStream = multipartFile.getInputStream();
            contentLength = multipartFile.getResource().contentLength();
        } catch (IOException e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        }
        ByteArrayOutputStream outputStream = cloneInputStream(inputStream);
        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        String imageHash = getImageHash(inputStream);
        ImageInfo imageInfo = imageInfoRepo.queryImageInfoByHash(userId, imageHash);

        ImageInfoVO imageInfoVO;
        if (Objects.nonNull(imageInfo)) {
            imageInfoVO = new ImageInfoVO(imageInfo.getId(), imageInfo.getImageName(), cosService.getImageUrl(imageInfo.getImagePath()));
        } else {
            String filename = getFilename(multipartFile.getContentType());
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return insertImage(userId, filename, imageHash, inputStream, contentLength);
        }

        return imageInfoVO;
    }
}
