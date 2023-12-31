package com.zero.iweiojbackend.model.common;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息消息枚举
 *
 * @author ZERO
 * @date 2023/12/30
 */
@Getter
public enum JudgeInfoMessageEnum {

    /**
     * 成功
     */
    ACCEPTED("Accepted", "成功"),

    WRONG_ANSWER("Wrong Answer", "答案错误"),

    COMPILE_ERROR("Compile Error", "编译错误"),

    MEMORY_LIMIT_EXCEEDED("Memory Limit Exceeded", "内存溢出"),

    TIME_LIMIT_EXCEEDED("Time Limit Exceeded", "超时"),

    PRESENTATION_ERROR("Presentation Error", "展示错误"),

    WAITING("Waiting", "等待中"),

    OUTPUT_LIMIT_EXCEEDED("Output Limit Exceeded", "输出溢出"),

    DANGEROUS_OPERATION("Dangerous Operation", "危险操作"),

    RUNTIME_ERROR("Runtime Error", "运行错误"),

    SYSTEM_ERROR("System Error", "系统错误");

    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String value, String text) {
        this.text = text;
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
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
