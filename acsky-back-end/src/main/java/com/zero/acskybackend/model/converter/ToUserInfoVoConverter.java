package com.zero.acskybackend.model.converter;

import com.zero.acskybackend.model.po.UserInfo;
import com.zero.acskybackend.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * UserInfo --> UserInfoVO
 *
 * @author ZERO
 * @date 2023/1/14
 */
@Mapper
public interface ToUserInfoVoConverter {
    ToUserInfoVoConverter CONVERTER = Mappers.getMapper(ToUserInfoVoConverter.class);

    /**
     * ToUserInfoVO
     * @param userInfo userInfo
     * @return UserInfoVO
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "account", source = "account")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "profile", source = "profile")
    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "createDate", source = "createDate")
    UserInfoVO toUserInfoVO(UserInfo userInfo);
}
