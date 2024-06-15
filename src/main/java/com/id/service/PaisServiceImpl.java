/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IPaisDao;
import com.id.domain.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class PaisServiceImpl implements PaisService{

    @Autowired
    private IPaisDao paisDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Pais> listarPaises() {
        return (List<Pais>) paisDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Pais pais) {
        paisDao.save(pais);
    }

    @Override
    @Transactional
    public void eliminar(Pais pais) {
        paisDao.delete(pais);
    }

    @Override
    @Transactional(readOnly = true)
    public Pais encontrarPais(Pais pais) {
        return paisDao.findById(pais.getIdPais()).orElse(pais);
    }
    
}
