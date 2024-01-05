package com.zero.iweiojbackend.repo;

import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.query.BaseQuery;
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
     * @param baseQuery 查询器
     * @return 结果
     */
    Long tagCount(BaseQuery baseQuery);

    /**
     * 标签关联总数
     * @param tagId tagId 标签 id
     * @return 结果
     */
    Long tagRelevancyCount(@Param("tagId") Number tagId);

}
