package com.zero.iweiojbackend.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.utils.IOUtils;
import com.zero.iweiojbackend.config.CosConfig;
import com.zero.iweiojbackend.exception.AssertionException;
import com.zero.iweiojbackend.model.common.ErrorCode;
import com.zero.iweiojbackend.service.CosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.*;

/**
 * CosServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("cosServiceImpl")
@RequiredArgsConstructor
public class CosServiceImpl implements CosService {

    private final CosConfig cosConfig;

    public COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
        Region region = new Region(cosConfig.getReginName());
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(cred, clientConfig);
    }

    public void shutdownCosClient(COSClient cosClient) {
        cosClient.shutdown();
    }

    public TransferManager getTransferManager() {
        COSClient cosClient = getCosClient();
        TransferManager transferManager = new TransferManager(cosClient, new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        ));
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5 * 1024 * 1024);
        transferManagerConfiguration.setMinimumUploadPartSize(1024 * 1024);
        transferManager.setConfiguration(transferManagerConfiguration);
        return transferManager;
    }

    public void shutdownTransferManager(TransferManager transferManager) {
        transferManager.shutdownNow(true);
    }

    public String putObject(InputStream inputStream, String key, Long contentLength) {

        TransferManager transferManager = getTransferManager();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(contentLength);

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(cosConfig.getBucketName(), cosConfig.getPrefix() + key, inputStream, objectMetadata);
        putObjectRequest.setStorageClass(StorageClass.Standard);

        try {
            UploadResult uploadResult = transferManager.upload(putObjectRequest).waitForUploadResult();
            return uploadResult.getKey();
        } catch (Exception e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        } finally {
            shutdownTransferManager(transferManager);
        }
    }

    public String getObject(String key) {

        COSClient cosClient = getCosClient();

        GetObjectRequest getObjectRequest =
                new GetObjectRequest(cosConfig.getBucketName(), cosConfig.getPrefix() + key);

        byte[] bytes;

        try {
            COSObject cosObject = cosClient.getObject(getObjectRequest);
            COSObjectInputStream objectContent = cosObject.getObjectContent();
            bytes = IOUtils.toByteArray(objectContent);
        } catch (Exception e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        } finally {
            shutdownCosClient(cosClient);
        }

        return new String(bytes);
    }

    public void deleteObject(String key) {
        COSClient cosClient = getCosClient();
        try {
            cosClient.deleteObject(cosConfig.getBucketName(), cosConfig.getPrefix() + key);
        } catch (Exception e) {
            throw new AssertionException(ErrorCode.OPERATION_ERROR);
        } finally {
            shutdownCosClient(cosClient);
        }
    }

    public String getImageUrl(String imageKey) {
        imageKey = getImageKey(imageKey);
        return cosConfig.getUrl() + cosConfig.getPrefix() + imageKey;
    }

    public String getImageKey(String imageUrl) {
        return imageUrl.replaceAll(cosConfig.getUrl(), "").replaceAll(cosConfig.getPrefix(), "");
    }
}
