package com.zero.acskybackend.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 页
 *
 * @author ZERO
 * @date 2023/6/30
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

}
