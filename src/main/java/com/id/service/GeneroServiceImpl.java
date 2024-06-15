/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IGeneroDao;
import com.id.domain.Genero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class GeneroServiceImpl implements GeneroService{
    
    @Autowired
    private IGeneroDao generoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Genero> listarGeneros() {
        return (List<Genero>) generoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Genero genero) {
        generoDao.save(genero);
    }

    @Override
    @Transactional
    public void eliminar(Genero genero) {
        generoDao.delete(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public Genero encontrarGenero(Genero genero) {
        return generoDao.findById(genero.getIdGenero()).orElse(genero);
    }
}
