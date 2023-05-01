package com.example.realtyservice.service;


import com.df.commonmodel.exceptions.CustomException;
import com.example.realtydto.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user) throws CustomException;

    Boolean deleteByID(Integer id);

    Boolean checkUserExist(String userName);

    Boolean checkEmailExist(String userName);

    UserDto getUserByUsername(String username);

    List<UserDto> loadAllUser();

    UserDto findById(int id);

    UserDto updateById(UserDto userDto) throws CustomException;
}
