/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.TipoPersona;
import java.util.List;

/**
 *
 * @author id
 */
public interface TipoPersonaService {
    
    public List<TipoPersona> listarTipoPersona();
    
    public void guardar(TipoPersona tipoPersona);
    
    public void eliminar(TipoPersona tipoPersona);
    
    public TipoPersona encontrarTipoPersona(TipoPersona tipoPersona);
}
