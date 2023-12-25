package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.query.BaseQuery;

import java.util.Collection;
import java.util.Collections;

/**
 * 基础 repo
 *
 * @author ZERO
 * @date 2023/11/28
 */
public interface BaseRepo<T> {

    /**
     * 批处理数据大小
     */
    Integer DEFAULT_BATCH_SIZE = 1000;


    /**
     * 保存一条数据
     *
     * @param t T
     * @return 受影响的行数
     */
    default Integer save(T t) {
        return -1;
    }

    /**
     * 保存所有数据
     *
     * @param list 待保存集合
     * @return 受影响的行数
     */
    default Integer saveAll(Collection<T> list) {
        return -1;
    }

    /**
     * 通过 id 删除数据
     *
     * @param id 删除序号
     * @return 受影响的行数
     */
    default Integer deleteById(Number id) {
        return -1;
    }

    /**
     * 通过 id 集合删除一批数据
     *
     * @param ids 序号集合
     * @return 受影响的行数
     */
    default Integer deleteByIds(Collection<Number> ids) {
        return -1;
    }

    /**
     * 通过 id 修改某条数据
     *
     * @param t 待修改数据
     * @return 受影响的行数
     */
    default Integer updateById(T t) {
        return -1;
    }

    /**
     * 通过 id 集合修改一批数据
     *
     * @param list 待修改数据集合
     * @return 受影响的行数
     */
    default Integer updateByIds(Collection<T> list) {
        return -1;
    }

    /**
     * 通过 id 查询
     *
     * @param id 待查询id
     * @return 结果
     */
    default T getById(Number id) {
        return null;
    }

    /**
     * 通过 id 集合查询
     *
     * @param ids 待查询id集合
     * @return 结果集
     */
    default Collection<T> getByIds(Collection<Number> ids) {
        return Collections.emptyList();
    }

    /**
     * 查询所有
     *
     * @return 返回所有
     */
    default Collection<T> getAll(BaseQuery query) {
        return Collections.emptyList();
    }

}
