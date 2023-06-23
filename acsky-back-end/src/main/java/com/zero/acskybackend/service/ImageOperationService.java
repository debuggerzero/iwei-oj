package com.zero.acskybackend.service;

import com.zero.acskybackend.exception.AssertionException;
import com.zero.acskybackend.model.common.GlobalExceptionEnum;
import com.zero.acskybackend.model.po.ImageInfo;
import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.ImageInfoVO;
import com.zero.acskybackend.repo.ImageInfoRepo;
import com.zero.acskybackend.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * FileOperationService
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ImageOperationService {

    private final CosService cosService;

    @Resource(name = "imageInfoRepoImpl")
    private final ImageInfoRepo imageInfoRepo;

    private String getFilename(String contentType) {
        if (Objects.isNull(contentType)) {
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
        }
        return StringUtil.uuid() + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
    }

    private String getImageHash(InputStream inputStream) {
        String hash;
        try {
            hash = DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
        }
        return hash;
    }

    private ImageInfoVO insertImage(
            Integer id,
            String name,
            String imageHash,
            InputStream inputStream,
            long contentLength
    ) {
        String key = "/" + id + "/assets/" + name;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        Integer length = insertImage(
                new ImageInfo(null, name, key, imageHash, userInfo, null)
        );
        if (length == 0) {
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
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
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
        }
        return outputStream;
    }

    public Integer insertImage(ImageInfo imageInfo) {
        return imageInfoRepo.insertImage(imageInfo);
    }

    public ImageInfoVO uploadImage(MultipartFile multipartFile, Integer id) {

        InputStream inputStream;
        long contentLength;
        try {
            inputStream = multipartFile.getInputStream();
            contentLength = multipartFile.getResource().contentLength();
        } catch (IOException e) {
            throw new AssertionException(GlobalExceptionEnum.FILE_UPLOAD_FAIL_EXCEPTION);
        }
        ByteArrayOutputStream outputStream = cloneInputStream(inputStream);
        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        String imageHash = getImageHash(inputStream);
        ImageInfo imageInfo = imageInfoRepo.queryImageInfoByHash(id, imageHash);

        ImageInfoVO imageInfoVO;
        if (Objects.nonNull(imageInfo)) {
            imageInfoVO = new ImageInfoVO(imageInfo.getId(), imageInfo.getImageName(), cosService.getImageUrl(imageInfo.getImagePath()));
        } else {
            String filename = getFilename(multipartFile.getContentType());
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return insertImage(id, filename, imageHash, inputStream, contentLength);
        }

        return imageInfoVO;
    }
}
