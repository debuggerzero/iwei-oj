package com.zero.iweiojbackend.repo.mapper;

import com.zero.iweiojbackend.model.po.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * HistoryMapper
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Mapper
public interface HistoryMapper {

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