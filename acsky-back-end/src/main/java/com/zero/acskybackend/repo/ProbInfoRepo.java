package com.zero.acskybackend.repo;

import com.zero.acskybackend.model.po.ProbInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * ProbInfoRepo
 *
 * @author wjh
 * @date 2023/6/20
 */
public interface ProbInfoRepo {

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
     * @param id 题目 Id
     * @return 题目信息
     */
    ProbInfo queryOne(@Param("id") Integer id);

}
