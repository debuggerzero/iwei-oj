package com.zero.acskybackend.repo;

import com.zero.acskybackend.model.po.History;

import java.util.List;

/**
 * HistoryRepo
 *
 * @author ZERO
 * @date 2023/7/3
 */
public interface HistoryRepo {

    /**
     * 插入历史记录
     * @param history 历史记录
     * @return 受影响的行数
     */
    Integer insertHistory(History history);

    /**
     * 查询历史记录列表
     * @param uid 用户列表
     * @return 历史记录列表
     */
    List<History> queryHistoryList(Integer uid);
}
