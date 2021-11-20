package com.company;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proveedores", schema = "bdgestionproyectos")
//@Table(name = "proveedores", schema = "bdgestionproyectos", catalog = "")

public class ProveedoresEntity implements Serializable {

 /*   @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;*/

    private int id;
    private String codigo;
    private String nombre;
    private String apellidos;
    private String direccion;
    private boolean baja;
    private String fechabaja;
    private Collection<GestionEntity> gestionsById;


    public ProveedoresEntity() {
    }

    public ProveedoresEntity(int id) {
        this.id = id;
    }

    //CON BAJAS Y FECHAS
    public ProveedoresEntity(String codigo, String nombre, String apellidos, String direccion, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    public ProveedoresEntity(int id, String codigo, String nombre, String apellidos, String direccion, boolean baja, String fechabaja, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.baja = baja;
        this.fechabaja = fechabaja;
        this.gestionsById = gestionsById;
    }

    //SIN BAJAS Y FECHAS
    public ProveedoresEntity(String codigo, String nombre, String apellidos, String direccion, Collection<GestionEntity> gestionsById) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.gestionsById = gestionsById;
    }

    public ProveedoresEntity(int id, String codigo, String nombre, String apellidos, String direccion, Collection<GestionEntity> gestionsById) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
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
    @Column(name = "APELLIDOS", nullable = false, length = 30)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "DIRECCION", nullable = false, length = 40)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        ProveedoresEntity that = (ProveedoresEntity) o;
        return id == that.id && Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(direccion, that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, apellidos, direccion);
    }

    @OneToMany(mappedBy = "proveedoresByCodproveedor")
    public Collection<GestionEntity> getGestionsById() {
        return gestionsById;
    }

    public void setGestionsById(Collection<GestionEntity> gestionsById) {
        this.gestionsById = gestionsById;
    }

    @Override
    public String toString() {
        return "PROVEEDOR: \n" +
                "\tCODIGO=" + codigo + "\n" +
                "\tNOMBRE=" + nombre + "\n" +
                "\tAPELLIDO=" + apellidos + "\n" +
                "\tDIRECCION=" + direccion
                ;
    }


    public String toStringBaja() {
        return "PROVEEDOR: "+ "\n" +
                "\tCODIGO=" + codigo + "\n" +
                "\tNOMBRE=" + nombre + "\n" +
                "\tAPELLIDO=" + apellidos + "\n" +
                "\tFECHA DE LA BAJA=" + fechabaja + "\n" +
                "ESTE PROVEEDOR PERMANECERÁ ASOCIADO A LOS PEDIDOS YA REALIZADOS"+ "\n" +
                "PERO SU BAJA ES UNA ACCION IRREVERSIBLE, NO PODRÁS EDITAR SUS DATOS A FUTURO"
                ;
    }

    public String toStringEliminar() {
        return "PROVEEDOR: "+ "\n" +
                "\tCODIGO=" + codigo + "\n" +
                "\tNOMBRE=" + nombre + "\n" +
                "\tAPELLIDO=" + apellidos + "\n" +
                "ESTE PROVEEDOR SE ELIMINARÁ COMPLETAMENTE DE LA BASE DE DATOS"+ "\n" +
                "ES UNA ACCION IRREVERSIBLE"
                ;
    }



}
