package com.zero.iweiojbackend.model.common;

import lombok.Getter;

/**
 * 语言类别
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Getter
public enum LanguageEnum {

    /**
     * cpp
     */
    CPP("c++"),

    /**
     * java
     */
    JAVA("java");

    /**
     * 语言名称
     */
    private final String name;

    LanguageEnum(String name) {
        this.name = name;
    }

}
