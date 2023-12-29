package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.ProbInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * ProbInfoRepo
 *
 * @author wjh
 * @date 2023/6/20
 */
public interface ProbInfoRepo extends BaseRepo<ProbInfo> {

    /**
     * 查询题目总数
     * @param status 是否禁用
     * @return 题目总数
     */
    Long queryTotal(Integer status);

    /**
     * 更新提交次数
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer updateSubmitCnt(Integer id);

    /**
     * 更新通过次数
     * @param id 题目 id
     * @return 受影响的行数
     */
    Integer updateAcceptCnt(Integer id);

    /**
     * 更新 tagInfo
     * @param proId 题目 id
     * @param tagIds 标签 ids
     * @return 受影响的行数
     */
    Integer insertTagInfoById(Integer proId, List<Integer> tagIds);

    /**
     * 删除 pro_tag 关联信息
     * @param proId 题目 id
     * @param tagIds 标签 ids
     * @return 受影响的行数
     */
    Integer deleteTagInfoById(Integer proId, List<Integer> tagIds);

}
