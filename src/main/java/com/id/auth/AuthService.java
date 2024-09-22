/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.dao.IUsuarioDao;
import com.id.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsuario(), request.getPassword()));
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Usuario o Contraseña incorrectos!");
        }
        
        UserDetails user = usuarioDao.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .build();
    }

    public AuthResponse register(Usuario request) {
                
//        if (usuarioDao.existsByEmail(request.getEmail())) {
//            throw new UserAlreadyExistsException("El usuario ya existe");
//        }
        
        Usuario usuario = Usuario.builder()
                .usuario(request.getUsuario())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPasswor()))
                .build();

        usuarioDao.save(usuario);

        return AuthResponse.builder()
                .build();
    }
}
