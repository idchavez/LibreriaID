/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Idioma;
import java.util.List;

/**
 *
 * @author id
 */
public interface IdiomaService {
    
    public List<Idioma> listarIdiomas();
    
    public void guardar(Idioma idioma);
    
    public void eliminar(Idioma idioma);
    
    public Idioma encontrarIdioma(Idioma idioma);
}
