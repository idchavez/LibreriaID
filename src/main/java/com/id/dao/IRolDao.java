/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author id
 */
public interface IRolDao extends JpaRepository<Rol, Integer>{
    Rol findByNombre(String nombre);
}
