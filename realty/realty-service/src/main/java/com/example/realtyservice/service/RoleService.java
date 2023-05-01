package com.example.realtyservice.service;



import com.example.realtydto.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAllRole();

    RoleDto findById(int id);
}
