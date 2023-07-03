package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.repo.HistoryRepo;
import com.zero.acskybackend.repo.mapper.HistoryMapper;
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
