/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Pais;
import java.util.List;

/**
 *
 * @author id
 */
public interface PaisService {
    
    public List<Pais> listarPaises();
    
    public void guardar(Pais pais);
    
    public void eliminar(Pais pais);
    
    public Pais encontrarPais(Pais pais);
    
}
