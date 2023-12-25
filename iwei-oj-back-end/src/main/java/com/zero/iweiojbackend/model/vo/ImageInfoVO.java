package com.zero.iweiojbackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ImageInfoVO
 *
 * @author ZERO
 * @date 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfoVO {

    private Integer id;

    private String name;

    private String url;

    public ImageInfoVO(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
