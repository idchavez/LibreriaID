/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Genero;
import java.util.List;

/**
 *
 * @author id
 */
public interface GeneroService {
    
    public List<Genero> listarGeneros();
    
    public void guardar(Genero genero);
    
    public void eliminar(Genero genero);
    
    public Genero encontrarGenero(Genero genero);
}
