package com.example.realtyservice.controller;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.FactoryException;
import com.df.commonmodel.models.ApiResponse;
import com.example.realtydto.dto.UserDto;
import com.example.realtyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = "User")
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "delete user")
    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.deleteByID(id)) {
            return new ApiResponse(HttpStatus.OK, "Success", new FactoryException()).build();
        }
        return new ApiResponse(HttpStatus.BAD_REQUEST, "Account cannot delete", new FactoryException()).build();
    }
    @ApiOperation(value = "Get All User")
    @GetMapping(value = "user/all")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ApiResponse<>(userService.loadAllUser(), HttpStatus.OK).build();
    }
    @ApiOperation(value = "Save user")
    @PostMapping(value = "/user/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDTO) {
        try {
            return new ApiResponse<>(userService.save(userDTO), HttpStatus.CREATED).build();
        }catch (CustomException e){
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }
    @ApiOperation(value = "Get user by username")
    @GetMapping(value = "/user/username={username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return new ApiResponse<>(userService.getUserByUsername(username), HttpStatus.OK).build();
    }
    @ApiOperation(value = "check email")
    @GetMapping(value = "/user/check-email={email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        if (userService.checkEmailExist(email)) {
            return new ApiResponse<>(true, HttpStatus.OK).build();
        } else {
            return new ApiResponse<>(false, HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value = "get user by id")
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable int id) {
        return new ApiResponse<>(userService.findById(id), HttpStatus.OK).build();
    }

    @ApiOperation(value = "Update user")
    @PutMapping(value = "/user")
    public ResponseEntity<UserDto> updateNew(@RequestBody UserDto userDto) {
        try {
            return new ApiResponse(userService.updateById(userDto), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }
}
