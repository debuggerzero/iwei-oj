package com.zero.iweiojbackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签信息
 *
 * @author ZERO
 * @date 2023/12/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagInfoVO {

    /**
     * 标签 id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String name;

}
