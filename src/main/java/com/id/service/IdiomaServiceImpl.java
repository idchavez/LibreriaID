/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IIdiomaDao;
import com.id.domain.Idioma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class IdiomaServiceImpl implements IdiomaService {

    @Autowired
    private IIdiomaDao idiomaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Idioma> listarIdiomas() {
        return (List<Idioma>) idiomaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Idioma genero) {
        idiomaDao.save(genero);
    }

    @Override
    @Transactional
    public void eliminar(Idioma genero) {
        idiomaDao.delete(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public Idioma encontrarIdioma(Idioma genero) {
        return idiomaDao.findById(genero.getIdIdioma()).orElse(genero);
    }
}
