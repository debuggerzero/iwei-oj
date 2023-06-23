package com.zero.acskybackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Cos 对象存储配置
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cos")
public class CosConfig {

    /**
     * 身份识别 Id
     */
    private String secretId;

    /**
     * 身份识别密钥
     */
    private String secretKey;

    /**
     * 桶地区
     */
    private String reginName;

    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * url
     */
    private String url;

    /**
     * 前缀地址
     */
    private String prefix;

}
