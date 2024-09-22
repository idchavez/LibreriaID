/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author id
 */
//Aqui va API creada-- controlador libros
//@RestController
//@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class DemoController {
    
    @PostMapping(value = "/")
    public String welcome(){
        return "index";
    }
}
