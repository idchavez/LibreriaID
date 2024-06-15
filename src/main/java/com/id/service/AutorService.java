/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Autor;
import java.util.List;

/**
 *
 * @author id
 */
public interface AutorService {
    
    public List<Autor> listarAutores();
    
    public void guardar(Autor autor);
    
    public void eliminar(Autor autor);
    
    public Autor encontrarAutor(Autor autor);
}
