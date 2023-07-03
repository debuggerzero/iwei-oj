package com.zero.acskybackend.repo.impl;

import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.repo.ProbInfoRepo;
import com.zero.acskybackend.repo.mapper.ProbInfoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZERO
 */
@RequiredArgsConstructor
@Repository("probInfoRepoImpl")
public class ProbInfoRepoImpl implements ProbInfoRepo {

    private final ProbInfoMapper probinfoMapper;

    @Override
    public Long queryTotal() {
        return probinfoMapper.queryTotal();
    }

    @Override
    public List<ProbInfo> queryAllList() {
        return probinfoMapper.queryAllList();
    }

    @Override
    public ProbInfo queryOne(@Param("id") Integer id) {
        return probinfoMapper.queryOne(id);
    }

}
