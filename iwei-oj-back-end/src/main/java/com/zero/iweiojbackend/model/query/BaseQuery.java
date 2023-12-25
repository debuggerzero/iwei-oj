package com.zero.iweiojbackend.model.query;

import com.zero.iweiojbackend.model.common.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础查询
 *
 * @author ZERO
 * @date 2023/12/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuery {

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 分页器
     */
    private Page page;

    /**
     * 是否删除
     */
    private Integer isDelete;

}
