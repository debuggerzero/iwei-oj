package com.zero.iweiojbackend.model.po;

import lombok.*;

/**
 * 用例
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

    /**
     * 样例 id
     */
    private Integer id;

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

    /**
     * 题目 Id
     */
    private Integer proId;
}
