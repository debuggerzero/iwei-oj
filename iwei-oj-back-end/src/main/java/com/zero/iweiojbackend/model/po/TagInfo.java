package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签信息
 *
 * @author ZERO
 * @date 2023/11/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagInfo {

    /**
     * id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String name;
}
