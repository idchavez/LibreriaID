/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IEditorialDao;
import com.id.domain.Editorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class EditorialServiceImpl implements EditorialService{

    @Autowired
    private IEditorialDao editorialDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {
        return (List<Editorial>) editorialDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Editorial editorial) {
        editorialDao.save(editorial);
    }

    @Override
    @Transactional
    public void eliminar(Editorial editorial) {
        editorialDao.delete(editorial);
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial encontrarEditorial(Editorial editorial) {
        return editorialDao.findById(editorial.getIdEditorial()).orElse(editorial);
    }
    
}
