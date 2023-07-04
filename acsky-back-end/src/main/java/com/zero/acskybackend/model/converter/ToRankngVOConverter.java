package com.zero.acskybackend.model.converter;

import com.zero.acskybackend.model.vo.RankingVO;
import com.zero.acskybackend.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToRankngVOConverter
 *
 * @author ZERO
 * @date 2023/7/4
 */
@Mapper
public interface ToRankngVOConverter {

    ToRankngVOConverter CONVERTER = Mappers.getMapper(ToRankngVOConverter.class);

    /**
     * ToRankingVO
     * @param userInfoVO userInfoVO
     * @return RankingVO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "submitCnt", target = "submitCnt")
    @Mapping(source = "passCnt", target = "passCnt")
    @Mapping(source = "profile", target = "profile")
    RankingVO toRankingVO(UserInfoVO userInfoVO);

}
