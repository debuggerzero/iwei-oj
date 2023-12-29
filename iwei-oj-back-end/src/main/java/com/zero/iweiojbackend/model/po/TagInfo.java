package com.zero.iweiojbackend.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 标签信息
 *
 * @author ZERO
 * @date 2023/11/28
 */
@Data
@Builder
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

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updatePerson;

    public TagInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
