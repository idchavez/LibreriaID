/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Pais;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author id
 */
public interface IPaisDao extends CrudRepository<Pais, Integer>{
    
}
