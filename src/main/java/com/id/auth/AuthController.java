/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.domain.Usuario;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author id
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping(value = "register")
    public String registerPage() {
        return "signUp";
    }
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody Usuario request, Model model, HttpSession session) {//Cambiar instancia de dominio
        
        try {
            AuthResponse authRes = authService.login(request);
            String usuario = authRes.getUsername();
            model.addAttribute("usuario", usuario);
            session.setAttribute("usuario", usuario);
            return ResponseEntity.ok(authRes);
        } catch (AuthenticationException e) {
            return (ResponseEntity<AuthResponse>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        
    }
    /*
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestParam String usuario, @RequestParam String email, @RequestParam String password) {
        Usuario request = new Usuario(usuario, email, password);
        authService.register(request);
        AuthResponse response = new AuthResponse();
        response.setUsername(request.getUsuario());
        response.setMessage("Usuario creado");
        return ResponseEntity.ok(response);
        
        //Usuario request = new Usuario(usuario, email, password);
        //return ResponseEntity.ok(authService.register(request));
    }*/
    
    @PostMapping("/register")
    public String register(Model model,@RequestParam String usuario, @RequestParam String email, @RequestParam String password) {
        try {
            Usuario request = new Usuario(usuario, email, password);
            authService.register(request);

            model.addAttribute("successMessage", "Registro exitoso. Inicia sesión.");
            return "signUp";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error en el registro. Inténtalo de nuevo.");
            System.out.println("message: " + e);
            return "/signUp";
        }
    }
}
