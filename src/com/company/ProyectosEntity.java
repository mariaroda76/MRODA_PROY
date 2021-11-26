package com.company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proyectos", schema = "bdgestionproyectos")
//@Table(name = "proyectos", schema = "bdgestionproyectos", catalog = "")


public class ProyectosEntity implements Serializable {
    private int id;
    private String codigo;
    private String nombre;
    private String ciudad;
    private boolean baja;
    private String fechabaja;
    private Collection<GestionEntity> gestionsById;

    public ProyectosEntity() {
    }

    public ProyectosEntity(int id) {
        this.id = id;
    }

    //CON BAJAS Y FECHAS
    public ProyectosEntity(int id, String codigo, String nombre, String ciudad, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    public ProyectosEntity(String codigo, String nombre, String ciudad, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    //SIN BAJAS Y FECHAS

    public ProyectosEntity(int id, String codigo, String nombre, String ciudad, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.gestionsById = gestionsById;
    }

    public ProyectosEntity(String codigo, String nombre, String ciudad, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
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

@Override
public String toString() {
    return "PROYECTO: \n" +
            "\tCODIGO=" + codigo + "\n" +
            "\tNOMBRE=" + nombre + "\n" +
            "\tCIUDAD=" + ciudad+ "\n"

            ;
}


    public String toStringBaja() {
        return "PROYECTO: "+ "\n" +
                "\tCODIGO=" + codigo + "\n" +
                "\tNOMBRE=" + nombre + "\n" +
                "\tCIUDAD=" + ciudad+ "\n"  +
                "\tFECHA DE LA BAJA=" + fechabaja + "\n" +
                "ESTE PROYECTO PERMANECERÁ ASOCIADO A LOS PEDIDOS YA REALIZADOS"+ "\n" +
                "PERO SU BAJA ES UNA ACCION IRREVERSIBLE, NO PODRÁS EDITAR SUS DATOS A FUTURO"
                ;
    }

    public String toStringEliminar() {
        return "PROYECTO: "+ "\n" +
                "\tCODIGO=" + codigo + "\n" +
                "\tNOMBRE=" + nombre + "\n" +
                "\tCIUDAD=" + ciudad+ "\n"  +
                "ESTE PROYECTO SE ELIMINARÁ COMPLETAMENTE DE LA BASE DE DATOS"+ "\n" +
                "ES UNA ACCION IRREVERSIBLE"
                ;
    }

}
