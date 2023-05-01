package com.example.realtyservice.mapper;

import com.example.realtydto.dto.RoleDto;
import com.example.realtyservice.entity.Role;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto roleToRoleDto(Role role);

    Role roleDtoToRole(RoleDto roleDto);

}


