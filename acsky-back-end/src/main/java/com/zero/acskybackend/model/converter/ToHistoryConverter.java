package com.zero.acskybackend.model.converter;

import com.zero.acskybackend.model.command.ProblemCommand;
import com.zero.acskybackend.model.po.History;
import com.zero.acskybackend.model.vo.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToHistoryConverter
 *
 * @author ZERO
 * @date 2023/7/3
 */
@Mapper
public interface ToHistoryConverter {

    ToHistoryConverter CONVERTER = Mappers.getMapper(ToHistoryConverter.class);

    @Mapping(source = "problemCommand.pid", target = "pid")
    @Mapping(source = "problemCommand.uid", target = "uid")
    @Mapping(source = "problemCommand.code", target = "code")
    @Mapping(source = "problemCommand.type", target = "type")
    @Mapping(source = "answer.message", target = "status")
    History toProbInfoVO(ProblemCommand problemCommand, Answer answer);

}
