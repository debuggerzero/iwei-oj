package com.zero.iweiojbackend.model.query;

import com.zero.iweiojbackend.model.common.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
     * 是否禁用
     */
    private Integer status;

    public static boolean isNull(BaseQuery baseQuery)  {
        if (Objects.isNull(baseQuery)) {
            return true;
        }
        // 若 page 为空就给他一个默认值
        Page page = baseQuery.getPage();
        baseQuery.setPage(Page.revise(page));
        return false;
    }

}
