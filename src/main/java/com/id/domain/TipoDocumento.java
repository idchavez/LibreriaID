/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author id
 */
@Entity
@Table(name = "tipos_documento")
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDoc", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDoc = :idTipoDoc"),
    @NamedQuery(name = "TipoDocumento.findByTipoDoc", query = "SELECT t FROM TipoDocumento t WHERE t.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "TipoDocumento.findByInicialesDoc", query = "SELECT t FROM TipoDocumento t WHERE t.inicialesDoc = :inicialesDoc")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_doc")
    private Integer idTipoDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Size(max = 4)
    @Column(name = "iniciales_doc")
    private String inicialesDoc;
    
    @OneToMany(mappedBy = "tipoDocPersona")
    private List<Persona> personaList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public TipoDocumento(Integer idTipoDoc, String tipoDoc) {
        this.idTipoDoc = idTipoDoc;
        this.tipoDoc = tipoDoc;
    }

    public Integer getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getInicialesDoc() {
        return inicialesDoc;
    }

    public void setInicialesDoc(String inicialesDoc) {
        this.inicialesDoc = inicialesDoc;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDoc != null ? idTipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDoc == null && other.idTipoDoc != null) || (this.idTipoDoc != null && !this.idTipoDoc.equals(other.idTipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id.domain.TipoDocumento[ idTipoDoc=" + idTipoDoc + " ]";
    }
    
}
