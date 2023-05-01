package com.example.realtyservice.service;

import com.example.realtydto.dto.UserDto;
import com.example.realtyservice.entity.User;
import com.example.realtyservice.mapper.UserMapper;
import com.example.realtyservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_00; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String AUTHORITIES_KEY = "Authors";

    public void addAuthentication(HttpServletResponse res, Authentication authentication, UserRepository userRepository,
                                  UserMapper userMapper) throws JsonProcessingException {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        User user = userRepository.findByUserName(authentication.getName());
        UserDto userDto = userMapper.userToUserDto(user);
        userDto.setPassword(null);
        ObjectMapper mapper = new ObjectMapper();
        String jsonDto = mapper.writeValueAsString(userDto);
        Date expiration=new Date(System.currentTimeMillis() + EXPIRATIONTIME);
        userDto.setExpiration(expiration);
        System.out.println(jsonDto);
        String JWT = Jwts.builder()
                .setSubject(jsonDto)
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader("access-control-expose-headers", "Authorization");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER_STRING);
            if (token != null) {
                // parse the token.
                JwtParser jwtParser = Jwts.parser()
                        .setSigningKey(SECRET);
                Claims claims = jwtParser
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                String user = claims
                        .getSubject();
                ObjectMapper mapper = new ObjectMapper();

                UserDto userDto = mapper.readValue(user, UserDto.class);
                Collection authorities =
                        Arrays.stream(claims
                                .get(AUTHORITIES_KEY).toString().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                return user != null ?
                        new UsernamePasswordAuthenticationToken(userDto.getUserName(), null, authorities) :
                        null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
