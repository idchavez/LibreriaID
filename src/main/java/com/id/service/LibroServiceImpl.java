/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.ILibroDao;
import com.id.domain.Libro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private ILibroDao libroDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Libro> listarLibros(String cadena) {
        if (cadena != null) {
            return libroDao.findAll(cadena);
        }
        return (List<Libro>) libroDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Libro libro) {
        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void eliminar(Libro libro) {
        libroDao.delete(libro);
    }

    @Override
    @Transactional(readOnly = true)
    public Libro encontrarLibro(Libro libro) {
        return libroDao.findById(libro.getIdLibro()).orElse(libro);
    }
    
}
