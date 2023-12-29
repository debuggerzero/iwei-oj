package com.zero.iweiojbackend.model.converter;

import com.zero.iweiojbackend.model.po.SystemRole;
import com.zero.iweiojbackend.model.vo.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * SystemRole --> UserRole
 *
 * @author ZERO
 * @date 2023/12/29
 */
@Mapper
public interface ToUserRoleConverter {

    ToUserRoleConverter CONVERTER = Mappers.getMapper(ToUserRoleConverter.class);

    /**
     * SystemRole --> UserRole
     * @param systemRole 系统角色
     * @return UserRole
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "describe", target = "describe")
    UserRole toUserRole(SystemRole systemRole);

}
