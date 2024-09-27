/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author id
 */
@Entity
@Table(name = "prestamos")
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByIdPrestamo", query = "SELECT p FROM Prestamo p WHERE p.idPrestamo = :idPrestamo"),
    @NamedQuery(name = "Prestamo.findByFechaPrestamo", query = "SELECT p FROM Prestamo p WHERE p.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "Prestamo.findByFechaEntrega", query = "SELECT p FROM Prestamo p WHERE p.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Prestamo.findByDetalles", query = "SELECT p FROM Prestamo p WHERE p.detalles = :detalles"),
    @NamedQuery(name = "Prestamo.findByEntregaReal", query = "SELECT p FROM Prestamo p WHERE p.entregaReal = :entregaReal"),
    @NamedQuery(name = "Prestamo.findByMulta", query = "SELECT p FROM Prestamo p WHERE p.multa = :multa")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prestamo")
    private Integer idPrestamo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prestamo")
    private String fechaPrestamo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    private String fechaEntrega;
    @Size(max = 100)
    private String detalles;
    @Column(name = "entrega_real")
    private String entregaReal;
    @Size(max = 50)
    private String multa;
    
    @JoinColumn(name = "Prestamo_libro", referencedColumnName = "id_Libro")
    @ManyToOne
    private Libro prestamolibro;
    @JoinColumn(name = "prestamo_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona prestamoPersona;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Prestamo(Integer idPrestamo, String fechaPrestamo, String fechaEntrega) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEntregaReal() {
        return entregaReal;
    }

    public void setEntregaReal(String entregaReal) {
        this.entregaReal = entregaReal;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public Libro getPrestamolibro() {
        return prestamolibro;
    }

    public void setPrestamolibro(Libro prestamolibro) {
        this.prestamolibro = prestamolibro;
    }

    public Persona getPrestamoPersona() {
        return prestamoPersona;
    }

    public void setPrestamoPersona(Persona prestamoPersona) {
        this.prestamoPersona = prestamoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestamo != null ? idPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.idPrestamo == null && other.idPrestamo != null) || (this.idPrestamo != null && !this.idPrestamo.equals(other.idPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id.domain.Prestamo[ idPrestamo=" + idPrestamo + " ]";
    }
    
}
