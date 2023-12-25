package com.zero.iweiojbackend.repo.impl;

import com.zero.iweiojbackend.model.po.History;
import com.zero.iweiojbackend.repo.HistoryRepo;
import com.zero.iweiojbackend.repo.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * HistoryRepoImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Repository("historyRepoImpl")
@RequiredArgsConstructor
public class HistoryRepoImpl implements HistoryRepo {

    private final HistoryMapper historyMapper;

    @Override
    public Integer insertHistory(History history) {
        return historyMapper.insertHistory(history);
    }

    @Override
    public List<History> queryHistoryList(Integer uid) {
        return historyMapper.queryHistoryList(uid);
    }

}
