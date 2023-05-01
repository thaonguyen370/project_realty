package com.example.realtyservice.service.impl;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.ErrorCode;
import com.example.realtydto.dto.UserDto;
import com.example.realtyservice.entity.Role;
import com.example.realtyservice.entity.User;
import com.example.realtyservice.mapper.UserMapper;
import com.example.realtyservice.repository.RoleRepository;
import com.example.realtyservice.repository.UserRepository;
import com.example.realtyservice.service.UserService;
import com.example.realtyservice.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;

    //    @Autowired
//    @Qualifier("passwordEncoder")
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) throws CustomException {
        User user = userMapper.userDtoToUser(userDto);
        if (checkUserDto(userDto)) {
            if (this.checkUserExist(user.getUserName()) || this.checkEmailExist(user.getEmail()))
                throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.ERROR_INVALID_DATA.toString());
            else {
                return userMapper.userToUserDto(userRepository.save(user));
            }
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.ERROR_INVALID_DATA.toString());
        }
    }


    @Override
    public Boolean deleteByID(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return false;
        for (Role role : user.getRoles()) {
            if (role.getRoleId() == 2)
                return false;
        }
        userRepository.deleteById(id);
        return true;
    }


    public Boolean checkUserExist(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    public Boolean checkEmailExist(String email) {
        if (email == null)
            return false;
        User user = userRepository.findByEmail(email);
        return user != null;
    }


    @Override
    public UserDto findById(int id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto updateById(UserDto userDto) throws CustomException {
        User user = userRepository.findById(userDto.getId()).orElse(null);
        if (user == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.ERROR_NOT_FOUND.toString());
        }
        try {
            User temp = userMapper.userDtoToUser(userDto);
            List<Role> roles = new ArrayList<>();
         //   temp.setPassword(passwordEncoder.encode(temp.getPassword()));
            temp.setPassword("123");
            temp.setRoles(roles);
            return userMapper.userToUserDto(userRepository.
                    save(temp));
        }catch (Exception e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }

    }


    @Override
    public List<UserDto> loadAllUser() {

        List<User> listUser = userRepository.findAll();
//        List<UserDto> listUserDto = new ArrayList<>();
//        List<Integer> listRole = new ArrayList<>();
//        for (User user : listUser) {
//            listRole.clear();
//            UserDto userDto = userMapper.userToUserDto(user);
//            for (Role role : user.getRoles()) {
//                listRole.add(role.getRoleId());
//            }
//            userDto.setListRoleId(listRole);
//            listUserDto.add(userDto);
//        }
//        return listUserDto;
        return listUser.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    private Boolean checkUserDto(UserDto userDTO) {
        return Validation.validateMail(userDTO.getEmail());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;
    }
//    public PasswordEncoder encoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}

