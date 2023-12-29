package com.zero.iweiojbackend.model.converter;

import com.zero.iweiojbackend.model.po.UserInfo;
import com.zero.iweiojbackend.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ToUserInfoConverter
 *
 * @author ZERO
 * @date 2023/12/24
 */
@Mapper
public interface ToUserInfoConverter {

    ToUserInfoConverter CONVERTER = Mappers.getMapper(ToUserInfoConverter.class);

    /**
     * ToUserInfoVO
     * @param userInfoVO userInfoVO
     * @return UserInfo
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "account", target = "account")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "submitCnt", target = "submitCnt")
    @Mapping(source = "acceptCnt", target = "acceptCnt")
    UserInfo toUserInfo(UserInfoVO userInfoVO);

}
