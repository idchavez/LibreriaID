/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author id
 */
public interface IPrestamoDao extends CrudRepository<Prestamo, Integer>{
 
    @Query("SELECT p FROM Prestamo p WHERE"
            + " CONCAT(p.fechaPrestamo,p.fechaEntrega,p.detalles,"
            + "p.prestamolibro.titulo,p.entregaReal,"
            + "p.prestamoPersona.primerNombre,p.multa)"
            + " LIKE %?1%")
    public List<Prestamo> findAll(String cadena);
}
