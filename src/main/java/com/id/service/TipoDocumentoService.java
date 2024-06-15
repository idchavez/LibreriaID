/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.TipoDocumento;
import java.util.List;

/**
 *
 * @author id
 */
public interface TipoDocumentoService {
    
    public List<TipoDocumento> listarTipoDocumento();
    
    public void guardar(TipoDocumento tipoDocumento);
    
    public void eliminar(TipoDocumento tipoDocumento);
    
    public TipoDocumento encontrarTipoDocumento(TipoDocumento tipoDocumento);
}
