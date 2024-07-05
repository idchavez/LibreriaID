/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author id
 */
//@RestController
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

//    @PostMapping(value = "login")
//    public ResponseEntity<AuthResponse> login(@RequestBody Usuario request){
//        return ResponseEntity.ok(authService.login(request));
//    }
//    
//    @PostMapping(value = "register")
//    public ResponseEntity<AuthResponse> register(@RequestBody Usuario request){
//        return ResponseEntity.ok(authService.register(request));
//    }
    @GetMapping(value = "login")
    public String loginPage() {
        return "login"; // nombre de la vista (login.html)
    }

    @GetMapping(value = "register")
    public String registerPage() {
        return "signUp"; // nombre de la vista (signup.html)
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody Usuario request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AuthResponse> register(@RequestBody Usuario request){
        return ResponseEntity.ok(authService.register(request));
    }
}
