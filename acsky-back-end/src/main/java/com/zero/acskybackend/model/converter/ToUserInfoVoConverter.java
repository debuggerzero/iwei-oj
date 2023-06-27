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
 * @date 2023/6/24
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
    UserInfoVO toUserInfoVO(UserInfo userInfo);
}
