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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author id
 */
@Entity
@Table(name = "libros")
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),// JOIN FETCH l.autorLibro JOIN FETCH l.editorialLibro JOIN FETCH l.estadoLibro JOIN FETCH l.generoLibro JOIN FETCH l.idiomaLibro"),
    @NamedQuery(name = "Libro.findByIdLibro", query = "SELECT l FROM Libro l WHERE l.idLibro = :idLibro"),
    @NamedQuery(name = "Libro.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Libro.findByPaginas", query = "SELECT l FROM Libro l WHERE l.paginas = :paginas"),
    @NamedQuery(name = "Libro.findByEdicion", query = "SELECT l FROM Libro l WHERE l.edicion = :edicion"),
    @NamedQuery(name = "Libro.findByFechaPublicacion", query = "SELECT l FROM Libro l WHERE l.fechaPublicacion = :fechaPublicacion")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Libro")
    private Integer idLibro;
    @Size(max = 60)
    @NotEmpty
    private String titulo;
    @Size(max = 50)
    @NotEmpty
    private String paginas;
    @Size(max = 20)
    @NotEmpty
    private String edicion;
    //@NotEmpty
    @Column(name = "fecha_Publicacion")
    //@Temporal(TemporalType.DATE)
    private String fechaPublicacion;
    
    //@NotNull
    @JoinColumn(name = "autor_libro", referencedColumnName = "id_autor")
    @ManyToOne
    private Autor autorLibro;
    @JoinColumn(name = "editorial_libro", referencedColumnName = "id_editorial")
    @ManyToOne
    private Editorial editorialLibro;
    @JoinColumn(name = "estado_libro", referencedColumnName = "id_estado")
    @ManyToOne
    private EstadoLibro estadoLibro;
    @JoinColumn(name = "genero_libro", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero generoLibro;
    @JoinColumn(name = "idioma_libro", referencedColumnName = "id_idioma")
    @ManyToOne(optional = false)
    private Idioma idiomaLibro;
    
    @OneToMany(mappedBy = "prestamolibro")
    private List<Prestamo> prestamoList;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Autor getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(Autor autorLibro) {
        this.autorLibro = autorLibro;
    }

    public Editorial getEditorialLibro() {
        return editorialLibro;
    }

    public void setEditorialLibro(Editorial editorialLibro) {
        this.editorialLibro = editorialLibro;
    }

    public EstadoLibro getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(EstadoLibro estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public Genero getGeneroLibro() {
        return generoLibro;
    }

    public void setGeneroLibro(Genero generoLibro) {
        this.generoLibro = generoLibro;
    }

    public Idioma getIdiomaLibro() {
        return idiomaLibro;
    }

    public void setIdiomaLibro(Idioma idiomaLibro) {
        this.idiomaLibro = idiomaLibro;
    }

    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.id.domain.Libro[ idLibro=" + idLibro + " ]";
    }

//    Error Sobrecarga    
//    @Override
//    public String toString() {
//        return "Libro{" + "idLibro=" + idLibro +
//               ", titulo=" + titulo + ", paginas=" + paginas + ", edicion=" + edicion +
//                ", fechaPublicacion=" + fechaPublicacion + ", autorLibro=" + autorLibro +
//                ", editorialLibro=" + editorialLibro + ", estadoLibro=" + estadoLibro + ","
//                + "generoLibro=" + generoLibro + ", idiomaLibro=" + idiomaLibro + ", prestamoList=" + prestamoList + '}';
//    }
    
    
    
}
