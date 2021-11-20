package com.company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "piezas", schema = "bdgestionproyectos")
//@Table(name = "piezas", schema = "bdgestionproyectos", catalog = "")

public class PiezasEntity implements Serializable {
    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private String descripcion;
    private boolean baja;
    private String fechabaja;
    private Collection<GestionEntity> gestionsById;


    public PiezasEntity() {
    }

    public PiezasEntity(int id) {
        this.id = id;
    }

    //CON BAJAS Y FECHAS
    public PiezasEntity(int id, String codigo, String nombre, double precio, String descripcion, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    public PiezasEntity(String codigo, String nombre, double precio, String descripcion, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    //SIN BAJAS Y FECHAS
    public PiezasEntity(int id, String codigo, String nombre, double precio, String descripcion, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.gestionsById = gestionsById;
    }

    public PiezasEntity(String codigo, String nombre, double precio, String descripcion, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.gestionsById = gestionsById;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CODIGO", nullable = false, length = 6)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "PRECIO", nullable = false, precision = 0)
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = -1)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "BAJA", nullable = true)
    public boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }


    @Basic
    @Column(name = "FECHABAJA", nullable = true, length = 10)
    public String getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(String fechabaja) {
        this.fechabaja = fechabaja;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiezasEntity that = (PiezasEntity) o;
        return id == that.id && Double.compare(that.precio, precio) == 0 && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, precio, descripcion);
    }

    @OneToMany(mappedBy = "piezasByCodpieza")
    public Collection<GestionEntity> getGestionsById() {
        return gestionsById;
    }

    public void setGestionsById(Collection<GestionEntity> gestionsById) {
        this.gestionsById = gestionsById;
    }
}
