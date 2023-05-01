package com.example.realtyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RealtyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtyServiceApplication.class, args);
        System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"));

    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //   return new BCryptPasswordEncoder();
    }

}
