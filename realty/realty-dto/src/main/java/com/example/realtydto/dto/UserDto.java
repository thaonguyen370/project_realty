package com.example.realtydto.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  {

    private static final long serialVersionUID = 1L;

    private Integer id;



    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String linkView;

    private String linkDownload;

    private List<RoleDto> roles=new ArrayList<>();

    private Date expiration;
}
