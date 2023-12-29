package com.zero.iweiojbackend.model.converter;

import com.zero.iweiojbackend.model.po.TagInfo;
import com.zero.iweiojbackend.model.vo.TagInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * TagInfo --> TagInfoVO
 *
 * @author ZERO
 * @date 2023/12/26
 */
@Mapper
public interface ToTagInfoVoConverter {

    ToTagInfoVoConverter CONVERTER = Mappers.getMapper(ToTagInfoVoConverter.class);

    /**
     * TagInfo --> TagInfoVO
     * @param tagInfo tagInfo
     * @return tagInfoVO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    TagInfoVO toTagInfoVO(TagInfo tagInfo);

}
