/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Persona;
import java.util.List;

/**
 *
 * @author id
 */
public interface PersonaService {
    
    public List<Persona> listarPersonas(String cadena);
    
    public void guardar(Persona persona);
    
    public void eliminar(Persona persona);
    
    public Persona encontrarPersona(Persona persona);
    
}
