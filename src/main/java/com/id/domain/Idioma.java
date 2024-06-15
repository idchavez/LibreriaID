/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import lombok.Data;

/**
 *
 * @author id
 */
@Data
@Entity
@Table(name = "idiomas")
//@NamedQueries({
//    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
//    @NamedQuery(name = "Idioma.findByIdIdioma", query = "SELECT i FROM Idioma i WHERE i.idIdioma = :idIdioma"),
//    @NamedQuery(name = "Idioma.findByIdioma", query = "SELECT i FROM Idioma i WHERE i.idioma = :idioma")})
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String idioma;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomaLibro")
    private List<Libro> libroList;

//    public Idioma() {
//    }
//
//    public Idioma(Integer idIdioma) {
//        this.idIdioma = idIdioma;
//    }
//
//    public Idioma(Integer idIdioma, String idioma) {
//        this.idIdioma = idIdioma;
//        this.idioma = idioma;
//    }
//
//    public Integer getIdIdioma() {
//        return idIdioma;
//    }
//
//    public void setIdIdioma(Integer idIdioma) {
//        this.idIdioma = idIdioma;
//    }
//
//    public String getIdioma() {
//        return idioma;
//    }
//
//    public void setIdioma(String idioma) {
//        this.idioma = idioma;
//    }
//
//    public List<Libro> getLibroList() {
//        return libroList;
//    }
//
//    public void setLibroList(List<Libro> libroList) {
//        this.libroList = libroList;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Idioma)) {
//            return false;
//        }
//        Idioma other = (Idioma) object;
//        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
//            return false;
//        }
//        return true;
//    }
//
    @Override
    public String toString() {
        return "com.id.domain.Idioma[ idIdioma=" + idIdioma + " ]";
    }
    
}
