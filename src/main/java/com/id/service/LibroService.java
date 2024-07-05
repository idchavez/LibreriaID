/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Libro;
import java.util.List;

/**
 *
 * @author id
 */
public interface LibroService {
    
    public List<Libro> listarLibros(String cadena);
    
    public void guardar(Libro libro);
    
    public void eliminar(Libro libro);
    
    public Libro encontrarLibro(Libro libro);
    
}
