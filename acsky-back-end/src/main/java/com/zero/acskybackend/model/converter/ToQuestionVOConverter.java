package com.zero.acskybackend.model.converter;

import com.zero.acskybackend.model.po.ProbInfo;
import com.zero.acskybackend.model.vo.QuestionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToQuestionVOConverter
 *
 * @author ZERO
 * @date 2023/7/4
 */
@Mapper
public interface ToQuestionVOConverter {

    ToQuestionVOConverter CONVERTER = Mappers.getMapper(ToQuestionVOConverter.class);

    /**
     * toQuestionVO
     * @param probInfo probInfo
     * @return QuestionVO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "difficulty", target = "difficulty")
    @Mapping(source = "timeLimit", target = "timeLimit")
    @Mapping(source = "spaceLimit", target = "spaceLimit")
    @Mapping(source = "passCnt", target = "passCnt")
    @Mapping(source = "submitCnt", target = "submitCnt")
    QuestionVO toQuestionVO(ProbInfo probInfo);

}
