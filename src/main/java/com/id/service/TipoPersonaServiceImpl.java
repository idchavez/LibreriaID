/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.ITipoPersonaDao;
import com.id.domain.TipoPersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class TipoPersonaServiceImpl implements TipoPersonaService{
    
    @Autowired
    private ITipoPersonaDao tipoPersonaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoPersona> listarTipoPersona() {
        return (List<TipoPersona>) tipoPersonaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(TipoPersona tipoPersona) {
        tipoPersonaDao.save(tipoPersona);
    }

    @Override
    @Transactional
    public void eliminar(TipoPersona tipoPersona) {
        tipoPersonaDao.delete(tipoPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoPersona encontrarTipoPersona(TipoPersona tipoPersona) {
        return tipoPersonaDao.findById(tipoPersona.getIdTipoPersona()).orElse(tipoPersona);
    }
}
