package com.zero.iweiojbackend.service;

import com.zero.iweiojbackend.model.dto.tag.TagInfoRequest;
import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
import com.zero.iweiojbackend.model.vo.GeneralCollectionResult;
import com.zero.iweiojbackend.model.vo.TagInfoVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * TagInfoService
 *
 * @author ZERO
 * @date 2023/12/25
 */
@Service
public interface TagInfoService {

    /**
     * 添加标签信息（管理员）
     *
     * @param tagInfoRequest tagInfoRequest
     * @param request        request
     * @return 受影响的行数
     */
    Integer insertTagInfo(TagInfoRequest tagInfoRequest, HttpServletRequest request);

    /**
     * 通过 id 删除标签（管理员）
     *
     * @param id 标签 id
     * @return 受影响的行数
     */
    Integer deleteTagInfoById(Integer id);

    /**
     * 更新标签信息通过 id（管理员）
     *
     * @param tagInfoRequest tagInfoRequest
     * @param request        request
     * @return 受影响的行数
     */
    Integer updateTagInfoById(TagInfoRequest tagInfoRequest, HttpServletRequest request);

    /**
     * 通过 id 查询标签详细信息（管理员）
     *
     * @param id 标签 id
     * @return 结果
     */
    TagInfo getTagInfoById(Integer id);

    /**
     * 查询标签结果集（管理员）
     *
     * @param query query
     * @return 结果集
     */
    GeneralCollectionResult<TagInfo> getTagInfoList(BaseQuery query);

    /**
     * 查询标签视图对象
     *
     * @return 结果集
     */
    GeneralCollectionResult<TagInfoVO> getTagInfoVOList();

}
