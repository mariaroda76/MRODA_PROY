package com.company;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gestion", schema = "bdgestionproyectos", catalog = "")
@IdClass(GestionEntityPK.class)
public class GestionEntity {
    private int codproveedor;
    private int codpieza;
    private int codproyecto;
    private Double cantidad;
    private ProveedoresEntity proveedoresByCodproveedor;
    private PiezasEntity piezasByCodpieza;
    private ProyectosEntity proyectosByCodproyecto;

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
}
