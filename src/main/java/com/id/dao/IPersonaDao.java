/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author id
 */
public interface IPersonaDao extends CrudRepository<Persona, Integer>{
    @Query("SELECT p FROM Persona p WHERE"
            + " CONCAT(p.primerNombre,p.segundoNombre,p.primerApellido,p.segundoApellido,"
            + "p.fechaNacimiento,p.tipoDePersona.tipoPersona,"
            + "p.tipoDocPersona.tipoDoc,p.numDocumento)"
            + " LIKE %?1%")
    public List<Persona> findAll(String cadena);
}
