package com.zero.acskybackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

}
