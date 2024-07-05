/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.dao;

import com.id.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author id
 */
public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByUsuario(String usuario);
    //Usuario findByUsuario(String usuario);
    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);
    
}
