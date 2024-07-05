/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author id
 */
public interface ILibroDao extends CrudRepository<Libro, Integer>{
    
    @Query("SELECT l FROM Libro l WHERE"
            + " CONCAT(l.titulo,l.paginas,l.edicion,"
            + "l.fechaPublicacion,l.autorLibro.alias,"
            + "l.editorialLibro.nomEditorial,l.estadoLibro.estado,"
            + "l.generoLibro.genero,l.idiomaLibro.idioma)"
            + " LIKE %?1%")
    public List<Libro> findAll(String cadena);
}
