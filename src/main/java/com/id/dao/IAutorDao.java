/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author id
 */
public interface IAutorDao extends CrudRepository<Autor, Integer>{
 
    @Query("SELECT a FROM Autor a WHERE"
            + " CONCAT(a.alias,a.biografia,a.estudios,"
            + "a.mail,a.alias,a.paisAutor.pais)"
            + " LIKE %?1%")
    public List<Autor> findAll(String cadena);
    
    //public List<Autor> findAll();
    
}
