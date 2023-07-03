package com.zero.acskybackend.repo.mapper;

import com.zero.acskybackend.model.po.ProbInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wjh
 */
@Mapper
public interface ProbInfoMapper {

    /**
     * 查询题目总数
     * @return 题目总数
     */
    Long queryTotal();

    /**
     * 查询所有题目
     * @return 题目列表
     */
    List<ProbInfo> queryAllList();

    /**
     * 查询一个题目
     * @param id 题目 id
     * @return 返回题目信息
     */
    ProbInfo queryOne(@Param("id") Integer id);

}
