package com.zero.iweiojbackend.utils;

import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文字工具类
 *
 * @author ZERO
 * @date 2023/1/13
 */
public class StringUtil {

    /**
     * 对字符串进行 md5 加密
     * @param value 待处理字符串
     * @return md5 加密后的字符串
     */
    public static String md5(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    /**
     * 生成 uuid
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成当前日期
     * @return 当前日期
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 判断是否为邮箱
     * @param value 待处理字符串
     * @return boolean
     */
    public static boolean isEmail(String value) {
        String expr = "^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";
        return value.matches(expr);
    }

    /**
     * 判断字符串是否为空
     * @param value 待处理字符串
     * @return boolean
     */
    public static boolean isEmpty(String value) {
        return  value == null || value.isEmpty();
    }

}
