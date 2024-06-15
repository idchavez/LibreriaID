/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.EstadoLibro;
import java.util.List;

/**
 *
 * @author id
 */
public interface EstadoLibroService {
    
    public List<EstadoLibro> listarEstadosLibros();
    
    public void guardar(EstadoLibro estadoLibro);
    
    public void eliminar(EstadoLibro estadoLibro);
    
    public EstadoLibro encontrarEstadoLibro(EstadoLibro estadoLibro);
}
