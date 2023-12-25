package com.zero.iweiojbackend.service.impl;

import com.zero.iweiojbackend.model.po.History;
import com.zero.iweiojbackend.repo.HistoryRepo;
import com.zero.iweiojbackend.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * HistoryServiceImpl
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service("historyServiceImpl")
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    @Resource(name = "historyRepoImpl")
    private HistoryRepo historyRepo;

    @Transactional(rollbackFor = Exception.class)
    public Integer insertHistory(History history) {
        return historyRepo.insertHistory(history);
    }

    public List<History> queryHistoryList(Integer uid) {
        return historyRepo.queryHistoryList(uid);
    }

}
