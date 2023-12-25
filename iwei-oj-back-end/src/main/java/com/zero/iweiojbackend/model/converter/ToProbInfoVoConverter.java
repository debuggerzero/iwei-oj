package com.zero.iweiojbackend.model.converter;

import com.zero.iweiojbackend.model.po.ProbInfo;
import com.zero.iweiojbackend.model.vo.ProbInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToProbInfoVO
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Mapper
public interface ToProbInfoVoConverter {

    ToProbInfoVoConverter CONVERTER = Mappers.getMapper(ToProbInfoVoConverter.class);

    /**
     * toProbInfoVO
     * @param probInfo probInfo
     * @return ProbInfoVO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "acceptCnt", target = "acceptCnt")
    @Mapping(source = "submitCnt", target = "submitCnt")
    @Mapping(source = "timeLimit", target = "timeLimit")
    @Mapping(source = "spaceLimit", target = "spaceLimit")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "tagInfos", target = "tagInfos")
    ProbInfoVO toProbInfoVO(ProbInfo probInfo);

}
