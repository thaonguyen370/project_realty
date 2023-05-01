package com.example.realtyservice.filtes;

import com.example.realtyservice.mapper.UserMapper;
import com.example.realtyservice.repository.UserRepository;
import com.example.realtyservice.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private UserRepository userRepository;
    private UserMapper userMapper;
    public JWTLoginFilter(String url, AuthenticationManager authManager,UserRepository userRepository,
                          UserMapper userMapper) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        String userName=request.getParameter("userName");
//        String password=request.getParameter("password");
        String userName="ThaoNguyen";
        String password="123456";
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password,
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService tokenAuthenticationService=new TokenAuthenticationService();
        tokenAuthenticationService.addAuthentication(response, authResult,userRepository,userMapper);
    }

}
