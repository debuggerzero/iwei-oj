package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.TagInfo;
import org.apache.ibatis.annotations.Param;

/**
 * TagInfoRepo
 *
 * @author ZERO
 * @date 2023/12/25
 */
public interface TagInfoRepo extends BaseRepo<TagInfo> {

    /**
     * 标签总数
     * @return 结果
     */
    Long tagCount();

    /**
     * 标签关联总数
     * @param tagId tagId 标签 id
     * @return 结果
     */
    Long tagRelevancyCount(@Param("tagId") Number tagId);

}
