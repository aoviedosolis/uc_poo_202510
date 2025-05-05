/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.taskapp.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author aoviedo
 */
@Entity
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private LocalDate fechaCreacion;
    @Column
    private LocalDate fechaVencimiento;
    @Column
    private boolean completado;
    @Column
    private int prioridad;
    
    @ManyToOne
    @JoinColumn
    private ListaTareas lista;
    
    @OneToMany(mappedBy = "tarea")
    private List<TareaEtiqueta> etiquetas;

    public Tarea() {
        this.id = 0L;
        this.nombre = "";
        this.descripcion = "";
        this.fechaCreacion = LocalDate.now();
        this.fechaVencimiento = LocalDate.now();
        this.completado = false;
        this.prioridad = 0;
    }

    public Tarea(String nombre, String descripcion, LocalDate fechaCreacion, LocalDate fechaVencimiento, boolean completado, int prioridad, ListaTareas lista) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaVencimiento = fechaVencimiento;
        this.completado = completado;
        this.prioridad = prioridad;
    }

    public ListaTareas getLista() {
        return lista;
    }

    public void setLista(ListaTareas lista) {
        this.lista = lista;
    }

    public List<TareaEtiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<TareaEtiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.edu.continental.poo.taskapp.entidades.Tarea[ id=" + id + " ]";
    }

    public Object[] toArray() {
        return new Object[]{id,nombre,descripcion,prioridad,completado,fechaCreacion,fechaVencimiento};
    }

}
