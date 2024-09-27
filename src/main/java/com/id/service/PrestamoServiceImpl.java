/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.IPrestamoDao;
import com.id.domain.Prestamo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private IPrestamoDao prestamoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listarPrestamos(String cadena) {
        if (cadena != null) {
            return prestamoDao.findAll(cadena);
        }
        return (List<Prestamo>) prestamoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Prestamo prestamo) {
        prestamoDao.save(prestamo);
    }

    @Override
    @Transactional
    public void eliminar(Prestamo prestamo) {
        prestamoDao.delete(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public Prestamo encontrarPrestamo(Prestamo prestamo) {
        return prestamoDao.findById(prestamo.getIdPrestamo()).orElse(prestamo);
    }
}
