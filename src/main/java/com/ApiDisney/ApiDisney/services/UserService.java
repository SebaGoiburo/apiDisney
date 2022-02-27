
package com.ApiDisney.ApiDisney.services;

import com.ApiDisney.ApiDisney.entities.Roles;
import com.ApiDisney.ApiDisney.entities.Username;
import com.ApiDisney.ApiDisney.error.ErrorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ApiDisney.ApiDisney.repository.UsernameRepository;
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UsernameRepository userRepository;

    @Transactional
    public void registerUser(String correo, String clave) throws ErrorService{
        Username user = new Username();
        user.setCorreo(correo);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        user.setClave(encriptada);
        user.setIsRemoved(false);
        userRepository.save(user);
        
    }
    
    @Transactional
    public void removedUser(Username user) throws ErrorService{
        Optional<Username> answer = userRepository.findById(user.getId());
        if(answer.isPresent()){
            Username us = answer.get();
            us.setIsRemoved(true);
            userRepository.save(us);
        } else{
            throw new ErrorService("No se halló el usuario buscado");
        }
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Username username = userRepository.findUsername(correo);
        if(username !=null){
            
            String token = getJWTToken(correo);
            
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROL_USER");
            GrantedAuthority p2 = new SimpleGrantedAuthority(token);
            permisos.add(p1);
            permisos.add(p2);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", username);

            User user = new User(username.getCorreo(), username.getClave(), permisos);
            return user;

        } else {
            return null;
        }
        }
    
        private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
                                .builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
    }
    }
    
    
    

