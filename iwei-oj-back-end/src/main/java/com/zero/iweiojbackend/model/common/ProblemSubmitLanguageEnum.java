package com.zero.iweiojbackend.model.common;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 语言类别
 *
 * @author ZERO
 * @date 2023/7/3
 */
public enum ProblemSubmitLanguageEnum {

    /**
     * Java
     */
    JAVA("java", "java"),

    /**
     * cpp
     */
    CPLUSPLUS("cpp", "cpp"),

    /**
     * go
     */
    GOLANG("go", "go"),

    /**
     * python
     */
    PYTHON("python", "python");

    private final String text;

    private final String value;

    ProblemSubmitLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
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
    public static ProblemSubmitLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ProblemSubmitLanguageEnum anEnum : ProblemSubmitLanguageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
