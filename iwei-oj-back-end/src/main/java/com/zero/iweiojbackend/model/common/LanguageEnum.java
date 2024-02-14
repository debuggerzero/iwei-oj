package com.zero.iweiojbackend.model.common;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 语言枚举类
 *
 * @author ZERO
 * @date 2024/2/12
 */
@Getter
public enum LanguageEnum {

    /**
     * java 0, c 1, cpp 2
     */
    JAVA("java"),
    C("c"),
    CPP("cpp");

    /**
     * value
     */
    private final String value;

    LanguageEnum(String value) {
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 结果集
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value value
     * @return 枚举值
     */
    public static LanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (LanguageEnum item : LanguageEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }

}
