package com.company;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proyectos", schema = "bdgestionproyectos", catalog = "")
public class ProyectosEntity {
    private int id;
    private String codigo;
    private String nombre;
    private String ciudad;
    private Collection<GestionEntity> gestionsById;

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
    @Column(name = "NOMBRE", nullable = false, length = 40)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = true, length = 40)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectosEntity that = (ProyectosEntity) o;
        return id == that.id && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(ciudad, that.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, ciudad);
    }

    @OneToMany(mappedBy = "proyectosByCodproyecto")
    public Collection<GestionEntity> getGestionsById() {
        return gestionsById;
    }

    public void setGestionsById(Collection<GestionEntity> gestionsById) {
        this.gestionsById = gestionsById;
    }
}
