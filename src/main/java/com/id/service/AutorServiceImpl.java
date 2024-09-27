/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IAutorDao;
import com.id.domain.Autor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class AutorServiceImpl implements AutorService{

    @Autowired
    private IAutorDao autorDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Autor> listarAutores(String cadena) {
        if (cadena != null) {
            return autorDao.findAll(cadena);
        }
        return (List<Autor>) autorDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Autor autor) {
        autorDao.save(autor);
    }

    @Override
    @Transactional
    public void eliminar(Autor autor) {
        autorDao.delete(autor);
    }

    @Override
    @Transactional(readOnly = true)
    public Autor encontrarAutor(Autor autor) {
        return autorDao.findById(autor.getIdAutor()).orElse(autor);
    }
    
}
