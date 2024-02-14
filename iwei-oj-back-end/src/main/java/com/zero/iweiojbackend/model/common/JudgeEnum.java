package com.zero.iweiojbackend.model.common;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息枚举
 *
 * @author ZERO
 * @date 2024/1/31
 */
@Getter
public enum JudgeEnum {

    /**
     * 成功0，展示错误1，超时2，内存溢出3，答案错误4，运行错误5，输出溢出6，编译错误7，系统错误8
     */
    ACCEPTED("Accepted", 0),

    PRESENTATION_ERROR("Presentation Error", 1),

    TIME_LIMIT_EXCEEDED("Time Limit Exceeded", 2),

    MEMORY_LIMIT_EXCEEDED("Memory Limit Exceeded", 3),

    WRONG_ANSWER("Wrong Answer", 4),

    RUNTIME_ERROR("Runtime Error", 5),

    OUTPUT_LIMIT_EXCEEDED("Output Limit Exceeded", 6),

    COMPILE_ERROR("Compile Error", 7),

    SYSTEM_ERROR("System Error", 8);

    private final String message;

    private final Integer value;

    JudgeEnum(String message, Integer value) {
        this.message = message;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 结果集
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value value
     * @return 枚举值
     */
    public static JudgeEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeEnum item : JudgeEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }

}
