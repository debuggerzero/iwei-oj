package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * PoiService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface PoiService {

    /**
     * 解析用户列表
     * @param file 用户信息表格
     * @return 用户信息列表
     * @throws IOException io 错误
     */
    List<UserInfo> getUserInfoList(InputStream file) throws IOException;

}
