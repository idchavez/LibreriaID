/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.domain.Libro;
import com.id.service.AutorService;
import com.id.service.EditorialService;
import com.id.service.EstadoLibroService;
import com.id.service.GeneroService;
import com.id.service.IdiomaService;
import com.id.service.LibroService;
import com.id.service.PaisService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    
    @ModelAttribute
    public void atributosComunes(HttpSession session, Model model){
        String usuario = (String) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        }
    }
    
    @GetMapping("/home")
    public String inicioApp() {
        return "index";
    }
    
    @GetMapping("/")
    public String enterApp() {
        return "login";
    }

    @GetMapping("/listadolibros")
    public String listadoLibros(Model model, @Param("cadena") String cadena) {
        log.info("Entrando al método inicio del controlador");
        var libros = libroService.listarLibros(cadena);
        model.addAttribute("libros", libros);
        model.addAttribute("cadena", cadena);
        return "libros";
    }
    
    @GetMapping("/agregar")
    public String agregar(Model model) {
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
    public String guardar(@Valid Model model,Libro libro, Errors errores, RedirectAttributes redirectAttributes) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/listadolibros";
        }
        //libroService.guardar(libro);
        System.out.println("libro guardado: " + libro);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro guardado.");
        return "redirect:/listadolibros";
    }

    @GetMapping("/editar/{idLibro}")
    public String editar(Model model, Libro libro, RedirectAttributes redirectAttributes) {
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
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro editado.");
        
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(Libro libro, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //libroService.eliminar(libro);
        //System.out.println("libro eliminado: " + libro);
        // Redirigir a la página anterior
//    String referer = request.getHeader("Referer"); // Obtiene la URL anterior
//    return "redirect:" + referer;
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado.");

    return "redirect:/listadolibros";
    }
}
