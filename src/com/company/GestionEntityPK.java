package com.company;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class GestionEntityPK implements Serializable {
    private int codproveedor;
    private int codpieza;
    private int codproyecto;

    @Column(name = "CODPROVEEDOR", nullable = false)
    @Id
    public int getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(int codproveedor) {
        this.codproveedor = codproveedor;
    }

    @Column(name = "CODPIEZA", nullable = false)
    @Id
    public int getCodpieza() {
        return codpieza;
    }

    public void setCodpieza(int codpieza) {
        this.codpieza = codpieza;
    }

    @Column(name = "CODPROYECTO", nullable = false)
    @Id
    public int getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(int codproyecto) {
        this.codproyecto = codproyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestionEntityPK that = (GestionEntityPK) o;
        return codproveedor == that.codproveedor && codpieza == that.codpieza && codproyecto == that.codproyecto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codproveedor, codpieza, codproyecto);
    }
}
