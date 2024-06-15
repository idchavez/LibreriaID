/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.web;

import com.id.domain.Libro;
import com.id.service.IdiomaService;
import com.id.service.LibroService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author id
 */
@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private LibroService libroService;

    @Autowired
    private IdiomaService idiomaService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Entrando al método inicio del controlador");
        var libros = libroService.listarLibros();
        System.out.println("Libros: " + libros);
        log.info("Ejecutando el Controlador Spring MVC");
        model.addAttribute("libros", libros);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Libro libro, Model model) {
        log.info("Entrando al método agregar");
        var idiomas = idiomaService.listarIdiomas();
        System.out.println("idiomas = " + idiomas);
        model.addAttribute("idiomas", idiomas);
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, Errors errores) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "modificar";
        }
        libroService.guardar(libro);
        return "redirect:/";
    }

    @GetMapping("/editar/{idLibro}")
    public String editar(Libro libro, Model model) {
        libro = libroService.encontrarLibro(libro);
        model.addAttribute("libro", libro);
        var idiomas = idiomaService.listarIdiomas();
        System.out.println("idiomas = " + idiomas);
        model.addAttribute("idiomas", idiomas);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Libro libro) {
        libroService.eliminar(libro);
        return "redirect:/";
    }
}
