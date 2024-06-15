/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.service;

import com.id.dao.ITipoDocumentoDao;
import com.id.domain.TipoDocumento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author id
 */
@Service
public class TipoDocumentoImpl implements TipoDocumentoService{

    @Autowired
    private ITipoDocumentoDao tipoDocumentoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumento> listarTipoDocumento() {
        return (List<TipoDocumento>) tipoDocumentoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(TipoDocumento tipoDocumento) {
        tipoDocumentoDao.save(tipoDocumento);
    }

    @Override
    @Transactional
    public void eliminar(TipoDocumento tipoDocumento) {
        tipoDocumentoDao.delete(tipoDocumento);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDocumento encontrarTipoDocumento(TipoDocumento tipoDocumento) {
        return tipoDocumentoDao.findById(tipoDocumento.getIdTipoDoc()).orElse(tipoDocumento);
    }
    
}
