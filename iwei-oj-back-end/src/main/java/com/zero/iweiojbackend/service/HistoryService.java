package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.po.History;

import java.util.List;

/**
 * HistoryService
 *
 * @author ZERO
 * @date 2023/11/26
 */
public interface HistoryService {

    /**
     * 插入记录
     * @param history 历史
     * @return 插入记录
     */
    Integer insertHistory(History history);

    /**
     * 查询历史记录
     * @param uid 用户 id
     * @return 记录列表
     */
    List<History> queryHistoryList(Integer uid);

}
