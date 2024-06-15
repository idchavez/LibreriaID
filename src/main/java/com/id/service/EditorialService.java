/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.domain.Editorial;
import java.util.List;

/**
 *
 * @author id
 */
public interface EditorialService {
    
    public List<Editorial> listarEditoriales();
    
    public void guardar(Editorial editorial);
    
    public void eliminar(Editorial editorial);
    
    public Editorial encontrarEditorial(Editorial editorial);
}
