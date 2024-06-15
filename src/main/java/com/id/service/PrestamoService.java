/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Prestamo;
import java.util.List;

/**
 *
 * @author id
 */
public interface PrestamoService {
    
    public List<Prestamo> listarPrestamos();
    
    public void guardar(Prestamo prestamo);
    
    public void eliminar(Prestamo prestamo);
    
    public Prestamo encontrarPrestamo(Prestamo prestamo);
}
