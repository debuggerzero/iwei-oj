package com.zero.acskybackend.model.converter;

import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.vo.ProbInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToProbInfoVO
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Mapper
public interface ToProbInfoVOConverter {

    ToProbInfoVOConverter CONVERTER = Mappers.getMapper(ToProbInfoVOConverter.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "passCnt", target = "passCnt")
    @Mapping(source = "submitCnt", target = "submitCnt")
    ProbInfoVO toProbInfoVO(ProbInfo probInfo);

}
