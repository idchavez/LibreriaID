/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.web;

import com.id.domain.Autor;
import com.id.domain.Editorial;
import com.id.domain.EstadoLibro;
import com.id.domain.Genero;
import com.id.domain.Idioma;
import com.id.domain.Libro;
import com.id.service.AutorService;
import com.id.service.EditorialService;
import com.id.service.EstadoLibroService;
import com.id.service.GeneroService;
import com.id.service.IdiomaService;
import com.id.service.LibroService;
import com.id.service.PaisService;
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
    private EditorialService editorialService;
    
    @Autowired
    private PaisService paisService;
    
    @Autowired
    private IdiomaService idiomaService;
    
    @Autowired
    private GeneroService generoService;
    
    @Autowired
    private AutorService autorService;
    
    @Autowired
    private EstadoLibroService estadoLibroService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Entrando al método inicio del controlador");
        var libros = libroService.listarLibros();
        log.info("Ejecutando el Controlador Spring MVC");
        model.addAttribute("libros", libros);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Libro libro, Editorial editorial, Idioma idioma, Genero genero, Autor autor, EstadoLibro estadoLibro, Model model) {
        log.info("Entrando al controlador agregar");
        var editoriales = editorialService.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        var paises = paisService.listarPaises();
        model.addAttribute("paises", paises);
        var idiomas = idiomaService.listarIdiomas();
        model.addAttribute("idiomas", idiomas);
        var generos = generoService.listarGeneros();
        model.addAttribute("generos", generos);
        var autores = autorService.listarAutores();
        model.addAttribute("autores", autores);
        var estadosLibro = estadoLibroService.listarEstadosLibros();
        model.addAttribute("estadosLibro", estadosLibro);
        return "agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Model model,Libro libro, Errors errores) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/";
        }
        libroService.guardar(libro);
        System.out.println("libro guardado: " + libro);
        return "redirect:/";
    }

    @GetMapping("/editar/{idLibro}")
    public String editar(Model model, Libro libro, Editorial editorial, Idioma idioma, Genero genero, Autor autor, EstadoLibro estadoLibro) {
        libro = libroService.encontrarLibro(libro);
        model.addAttribute("libro", libro);
        var editoriales = editorialService.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        var paises = paisService.listarPaises();
        model.addAttribute("paises", paises);
        var idiomas = idiomaService.listarIdiomas();
        model.addAttribute("idiomas", idiomas);
        var generos = generoService.listarGeneros();
        model.addAttribute("generos", generos);
        var autores = autorService.listarAutores();
        model.addAttribute("autores", autores);
        var estadosLibro = estadoLibroService.listarEstadosLibros();
        model.addAttribute("estadosLibro", estadosLibro);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Libro libro) {
        libroService.eliminar(libro);
        System.out.println("libro eliminado: " + libro);
        return "redirect:/";
    }
}
