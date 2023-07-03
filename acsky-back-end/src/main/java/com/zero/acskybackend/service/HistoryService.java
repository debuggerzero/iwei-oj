package com.zero.acskybackend.service;

import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.repo.HistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * HistoryService
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class HistoryService {

    @Resource(name = "historyRepoImpl")
    private final HistoryRepo historyRepo;

    public Integer insertHistory(History history) {
        return historyRepo.insertHistory(history);
    }

    public List<History> queryHistoryList(Integer uid) {
        return historyRepo.queryHistoryList(uid);
    }

}
