package com.zero.iweiojbackend.model.converter;

import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * UserInfo --> UserInfoVO
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Mapper
public interface ToUserInfoVoConverter {
    ToUserInfoVoConverter CONVERTER = Mappers.getMapper(ToUserInfoVoConverter.class);

    /**
     * ToUserInfoVO
     * @param userInfo userInfo
     * @return UserInfoVO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "account", target = "account")
    @Mapping(source = "email", target = "phone")
    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "submitCnt", target = "submitCnt")
    @Mapping(source = "acceptCnt", target = "acceptCnt")
    UserInfoVO toUserInfoVO(UserInfo userInfo);
}
