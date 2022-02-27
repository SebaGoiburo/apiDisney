package com.ApiDisney.ApiDisney;

import com.ApiDisney.ApiDisney.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiDisneyApplication {

        @Autowired
        UserService userService;
    
        public static void main(String[] args) {
		SpringApplication.run(ApiDisneyApplication.class, args);
	}

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        }    
        
}
