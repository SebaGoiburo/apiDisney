/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ApiDisney.ApiDisney.controllers;

import com.ApiDisney.ApiDisney.entities.Username;
import com.ApiDisney.ApiDisney.error.ErrorService;
import com.ApiDisney.ApiDisney.services.UserService;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class UserController {
 
    @Autowired
    UserService userService;
    
    @PostMapping("/register")
    public String registrar(ModelMap modelo, @RequestParam String correo, @RequestParam String clave) throws ErrorService{
        try {
            userService.registerUser(correo, clave);
            return "Successfully registered user";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("correo", correo);
            return "Failed registration";
        }
    }
    
//    @PostMapping("/login")
//	public Username login(@RequestParam("correo") String correo, @RequestParam("clave") String clave) {
//		Username user = userService.loadUserByUsername(correo);
//		Username username = new Username();
//		username.setCorreo(correo);
//		username.setToken(token);		
//		return user;
//		
//	}
    
      

}