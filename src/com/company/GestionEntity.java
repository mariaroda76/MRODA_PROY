package com.company;

import com.company.Controllers.PiezaController;
import com.company.Controllers.ProveedorController;
import com.company.Controllers.ProyectoController;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "gestion", schema = "bdgestionproyectos")
//@Table(name = "gestion", schema = "bdgestionproyectos", catalog = "")


@IdClass(GestionEntityPK.class)
public class GestionEntity implements Serializable {
    private int codproveedor;
    private int codpieza;
    private int codproyecto;
    private Double cantidad;
    private ProveedoresEntity proveedoresByCodproveedor;
    private PiezasEntity piezasByCodpieza;
    private ProyectosEntity proyectosByCodproyecto;


    public GestionEntity() {
    }

    public GestionEntity(int codproveedor) {
        this.codproveedor = codproveedor;
    }

    public GestionEntity(int codproveedor, int codpieza, int codproyecto, Double cantidad, ProveedoresEntity proveedoresByCodproveedor, PiezasEntity piezasByCodpieza, ProyectosEntity proyectosByCodproyecto) {
        this.codproveedor = codproveedor;
        this.codpieza = codpieza;
        this.codproyecto = codproyecto;
        this.cantidad = cantidad;
        this.proveedoresByCodproveedor = proveedoresByCodproveedor;
        this.piezasByCodpieza = piezasByCodpieza;
        this.proyectosByCodproyecto = proyectosByCodproyecto;
    }

    @Id
    @Column(name = "CODPROVEEDOR", nullable = false)
    public int getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(int codproveedor) {
        this.codproveedor = codproveedor;
    }

    @Id
    @Column(name = "CODPIEZA", nullable = false)
    public int getCodpieza() {
        return codpieza;
    }

    public void setCodpieza(int codpieza) {
        this.codpieza = codpieza;
    }

    @Id
    @Column(name = "CODPROYECTO", nullable = false)
    public int getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(int codproyecto) {
        this.codproyecto = codproyecto;
    }

    @Basic
    @Column(name = "CANTIDAD", nullable = true, precision = 0)
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestionEntity that = (GestionEntity) o;
        return codproveedor == that.codproveedor && codpieza == that.codpieza && codproyecto == that.codproyecto && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codproveedor, codpieza, codproyecto, cantidad);
    }

    @ManyToOne
    @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "ID", nullable = false)
    public ProveedoresEntity getProveedoresByCodproveedor() {
        return proveedoresByCodproveedor;
    }

    public void setProveedoresByCodproveedor(ProveedoresEntity proveedoresByCodproveedor) {
        this.proveedoresByCodproveedor = proveedoresByCodproveedor;
    }

    @ManyToOne
    @JoinColumn(name = "CODPIEZA", referencedColumnName = "ID", nullable = false)
    public PiezasEntity getPiezasByCodpieza() {
        return piezasByCodpieza;
    }

    public void setPiezasByCodpieza(PiezasEntity piezasByCodpieza) {
        this.piezasByCodpieza = piezasByCodpieza;
    }

    @ManyToOne
    @JoinColumn(name = "CODPROYECTO", referencedColumnName = "ID", nullable = false)
    public ProyectosEntity getProyectosByCodproyecto() {
        return proyectosByCodproyecto;
    }

    public void setProyectosByCodproyecto(ProyectosEntity proyectosByCodproyecto) {
        this.proyectosByCodproyecto = proyectosByCodproyecto;
    }


    public String CodproyectoERP() {
        ProyectosEntity proy = ProyectoController.selectproyectoById(codproyecto);
        return proy.getCodigo();
    }

    public String CodprovedorERP() {
        ProveedoresEntity proveedor = ProveedorController.selectProveedorById(codproveedor);
        return proveedor.getCodigo();
    }

    public String CodpiezaERP() {
        PiezasEntity pieza = PiezaController.selectPiezaById(codpieza);
        return pieza.getCodigo();
    }


    @Override
    public String toString() {


        return "PEDIDO: \n" +
                "\tCOD PROVEEDOR=" + CodprovedorERP() + "\n" +
                "\tCOD PIEZA=" + CodpiezaERP()+ "\n" +
                "\tCOD PROYECTO=" + CodproyectoERP() + "\n" +
                "\tCANTIDAD=" + cantidad+ "\n"
                ;
    }


    public String toStringBaja() {


        return "PEDIDO: \n" +
                "\tCOD PROVEEDOR=" + CodprovedorERP() + "\n" +
                "\tCOD PIEZA=" + CodpiezaERP()+ "\n" +
                "\tCOD PROYECTO=" + CodproyectoERP() + "\n" +
                "SE ELIMINARÁ EL PEDIDO"

                ;
    }

    public String toStringEliminar() {


        return "PEDIDO: \n" +
                "\tCOD PROVEEDOR=" + CodprovedorERP() + "\n" +
                "\tCOD PIEZA=" + CodpiezaERP()+ "\n" +
                "\tCOD PROYECTO=" + CodproyectoERP() + "\n" +
                "SE ELIMINARÁ EL PEDIDO"
                ;
    }


}
