package com.zero.iweiojbackend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * 通用集合返回类
 *
 * @author ZERO
 * @date 2023/12/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralCollectionResult<T> {

    /**
     * 结果集
     */
    Collection<T> collection;

    /**
     * 记录总条数
     */
    Long count;

}
