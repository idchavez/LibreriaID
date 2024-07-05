/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.dao.IUsuarioDao;
import com.id.domain.Rol;
import com.id.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author id
 */
@Service
@RequiredArgsConstructor
class AuthService {
    
    private final IUsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(Usuario request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPassword()));
        UserDetails user = usuarioDao.findByUsuario(request.getUsuario()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    
    public AuthResponse register(Usuario request) {
        Usuario usuario = Usuario.builder()
                .usuario(request.getUsuario())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        
        usuarioDao.save(usuario);
        
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
