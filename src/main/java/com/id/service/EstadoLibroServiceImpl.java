/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IEstadoLibroDao;
import com.id.domain.EstadoLibro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class EstadoLibroServiceImpl implements EstadoLibroService {

    @Autowired
    private IEstadoLibroDao estadoLibroDao;

    @Override
    @Transactional(readOnly = true)
    public List<EstadoLibro> listarEstadosLibros() {
        return (List<EstadoLibro>) estadoLibroDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(EstadoLibro estadoLibro) {
        estadoLibroDao.save(estadoLibro);
    }

    @Override
    @Transactional
    public void eliminar(EstadoLibro estadoLibro) {
        estadoLibroDao.delete(estadoLibro);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoLibro encontrarEstadoLibro(EstadoLibro estadoLibro) {
        return estadoLibroDao.findById(estadoLibro.getIdEstado()).orElse(estadoLibro);
    }

}
