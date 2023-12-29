package com.zero.iweiojbackend.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 页
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    /**
     * 页码
     */
    private Long pageNumber=1L;

    /**
     * 页号
     */
    private Long pageSize=10L;

    public static boolean isNull(Page page) {
        return Objects.isNull(page) || Objects.isNull(page.pageNumber) || Objects.isNull(page.pageSize);
    }

    public static Page revise(Page page) {
        if (!isNull(page)) {
            page.setPageNumber((page.getPageNumber() - 1) * page.getPageSize());
            if (page.getPageNumber() < 0 || page.getPageSize() <= 0) {
                return new Page(0L, 10L);
            }
        } else {
            return new Page(0L, 10L);
        }
        return page;
    }

}
