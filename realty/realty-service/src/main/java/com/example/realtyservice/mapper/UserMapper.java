package com.example.realtyservice.mapper;

import com.example.realtydto.dto.RoleDto;
import com.example.realtydto.dto.UserDto;
import com.example.realtyservice.entity.Role;
import com.example.realtyservice.entity.User;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    UserDto userToUserDto(User user);
    @Mapping(target = "roles", ignore = true)
    User userDtoToUser(UserDto userDto);

    @AfterMapping
    default void convertDtoToEntity(UserDto userDto, @MappingTarget User user) {
        try {
            List<Role> roles = userDto.getRoles().stream().map(r -> {
                Role role = new Role();
                role.setRoleId(r.getRoleId());
                return role;
            }).collect(Collectors.toList());
            user.setRoles(roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterMapping
    default void convertEntityToDto(User user, @MappingTarget UserDto userDto) {
        try {
            List<RoleDto> roles = user.getRoles().stream().map(r -> {
                RoleDto role = new RoleDto();
                role.setRoleId(r.getRoleId());
                role.setRoleName(r.getRoleName());
                return role;
            }).collect(Collectors.toList());
            userDto.setRoles(roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
