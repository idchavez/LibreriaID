/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.auth;

import com.id.domain.*;
import com.id.service.*;
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
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private PrestamoService prestamoService;
    
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    
    @Autowired
    private TipoPersonaService tipoPersonaService;
    
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
        //model.addAttribute("cadena", cadena);
        return "/vista-libros/libros";
    }
    
    @GetMapping("/listadoAutores")
    public String listadoAutores(Model model, @Param("cadena") String cadena) {
        var autores = autorService.listarAutores(cadena);
        model.addAttribute("autores", autores);
        model.addAttribute("cadena", cadena);
        return "/vista-autores/autores";
    }
    
    @GetMapping("/listadoPersonas")
    public String listadoPersonas(Model model, @Param("cadena") String cadena) {
        var personas = personaService.listarPersonas(cadena);
        model.addAttribute("personas", personas);
        model.addAttribute("cadena", cadena);
        return "/vista-personas/personas";
    }
    
    @GetMapping("/listadoPrestamos")
    public String listadoPrestamos(Model model, @Param("cadena") String cadena) {
        var prestamos = prestamoService.listarPrestamos(cadena);
        model.addAttribute("prestamos", prestamos);
        model.addAttribute("cadena", cadena);
        return "/vista-prestamos/prestamos";
    }
    
    @GetMapping("/agregar")
    public String agregar(Model model, @Param("cadena") String cadena) {
        log.info("Entrando al controlador agregar");
        var editoriales = editorialService.listarEditoriales();
        model.addAttribute("editoriales", editoriales);
        var paises = paisService.listarPaises();
        model.addAttribute("paises", paises);
        var idiomas = idiomaService.listarIdiomas();
        model.addAttribute("idiomas", idiomas);
        var generos = generoService.listarGeneros();
        model.addAttribute("generos", generos);
        var autores = autorService.listarAutores(cadena);
        model.addAttribute("autores", autores);
        var estadosLibro = estadoLibroService.listarEstadosLibros();
        model.addAttribute("estadosLibro", estadosLibro);
        return "/vista-libros/agregar";
    }
    
    @GetMapping("/agregarAutor")
    public String agregarAutor(Model model, @Param("cadena") String cadena) {
        var paises = paisService.listarPaises();
        model.addAttribute("paises", paises);
        var personas = personaService.listarPersonas(cadena);
        model.addAttribute("personas", personas);
        
        return "/vista-autores/agregarAutor";
    }
    
    @GetMapping("/agregarPersona")
    public String agregarPersona(Model model) {
        var tiposPersona = tipoPersonaService.listarTipoPersona();
        model.addAttribute("tiposPersona", tiposPersona);
        var tiposDocumento = tipoDocumentoService.listarTipoDocumento();
        model.addAttribute("tiposDocumento", tiposDocumento);
        
        return "/vista-personas/agregarPersona";
    }
    
    @GetMapping("/agregarPrestamo")
    public String agregarPrestamo(Model model, @Param("cadena") String cadena) {
        var libros = libroService.listarLibros(cadena);
        model.addAttribute("libros", libros);
        var personas = personaService.listarPersonas(cadena);
        model.addAttribute("personas", personas);
        
        return "/vista-prestamos/agregarPrestamo";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Model model,Libro libro, Errors errores, RedirectAttributes redirectAttributes) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/listadolibros";
        }
        libroService.guardar(libro);
        System.out.println("libro guardado: " + libro);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro guardado.");
        return "redirect:/listadolibros";
    }
    
    @PostMapping("/guardarAutor")
    public String guardarAutor(@Valid Model model,Autor autor, Errors errores, RedirectAttributes redirectAttributes) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/listadoAutores";
        }
        autorService.guardar(autor);
        System.out.println("autor guardado: " + autor);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro guardado.");
        return "redirect:/listadoAutores";
    }
    
    @PostMapping("/guardarPersona")
    public String guardarPersona(@Valid Model model,Persona persona, Errors errores, RedirectAttributes redirectAttributes) {
        System.out.println("Entrando a guardar....");
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/listadoPersonas";
        }
        personaService.guardar(persona);
        System.out.println("registro guardado: " + persona);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro guardado.");
        return "redirect:/listadoPersonas";
    }
    
    @PostMapping("/guardarPrestamo")
    public String guardarPrestamo(@Valid Model model,Prestamo prestamo, Errors errores, RedirectAttributes redirectAttributes) {
        if (errores.hasErrors()) {
            System.out.println("errores = " + errores);
            return "redirect:/listadoPrestamos";
        }
        prestamoService.guardar(prestamo);
        System.out.println("registro guardado: " + prestamo);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro guardado.");
        return "redirect:/listadoPrestamos";
    }
    
    @GetMapping("/editar/{idLibro}")
    public String editar(Model model, Libro libro, @Param("cadena") String cadena, RedirectAttributes redirectAttributes) {
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
        var autores = autorService.listarAutores(cadena);
        model.addAttribute("autores", autores);
        var estadosLibro = estadoLibroService.listarEstadosLibros();
        model.addAttribute("estadosLibro", estadosLibro);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro editado.");
        
        return "/vista-libros/modificar";
    }
    
    @GetMapping("/editarAutor/{idAutor}")
    public String editarAutor(@Param("cadena") String cadena, Model model, Autor autor, RedirectAttributes redirectAttributes) {
        autor = autorService.encontrarAutor(autor);
        model.addAttribute("autor", autor);
        var paises = paisService.listarPaises();
        model.addAttribute("paises", paises);
        var personas = personaService.listarPersonas(cadena);
        model.addAttribute("personas", personas);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro editado.");
        
        return "/vista-autores/modificarAutor";
    }

    @GetMapping("/editarPrestamo/{idPrestamo}")
    public String editarPrestamo(Model model, String cadena, Prestamo prestamo, RedirectAttributes redirectAttributes) {
        prestamo = prestamoService.encontrarPrestamo(prestamo);
        model.addAttribute("prestamo", prestamo);
        var libros = libroService.listarLibros(cadena);
        model.addAttribute("libros", libros);
        var personas = personaService.listarPersonas(cadena);
        model.addAttribute("personas", personas);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro editado.");
        
        return "/vista-prestamos/modificar";
    }
    
    @GetMapping("/editarPersona/{idPersona}")
    public String editarPersona(Model model, Persona persona, RedirectAttributes redirectAttributes) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        var tiposPersona = tipoPersonaService.listarTipoPersona();
        model.addAttribute("tiposPersona", tiposPersona);
        var tiposDocumento = tipoDocumentoService.listarTipoDocumento();
        model.addAttribute("tiposDocumento", tiposDocumento);
        
        redirectAttributes.addFlashAttribute("mensaje", "Registro editado.");
        
        return "/vista-personas/modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Libro libro, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        libroService.eliminar(libro);
        System.out.println("libro eliminado: " + libro);
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado.");

    return "redirect:/listadolibros";
    }
    
    @GetMapping("/eliminarAutor")
    public String eliminarAutor(Autor autor, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        autorService.eliminar(autor);
        System.out.println("autor eliminado: " + autor);
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado.");

    return "redirect:/listadoAutores";
    }
    
    @GetMapping("/eliminarPersona")
    public String eliminarPersona(Persona persona, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        personaService.eliminar(persona);
        System.out.println("registro eliminado: " + persona);
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado.");

    return "redirect:/listadoPersonas";
    }
    
    @GetMapping("/eliminarPrestamo")
    public String eliminarPrestamo(Prestamo prestamo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        prestamoService.eliminar(prestamo);
        System.out.println("registro eliminado: " + prestamo);
        redirectAttributes.addFlashAttribute("mensaje", "Registro eliminado.");

    return "redirect:/listadoPrestamos";
    }
}
