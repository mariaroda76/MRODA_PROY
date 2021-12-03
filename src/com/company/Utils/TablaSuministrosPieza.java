package com.company.Utils;

import com.company.GestionEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablaSuministrosPieza extends AbstractTableModel {

    private String[] columnas = {"CODIGO PROYECTO",
            "CODIGO PROVEEDOR",
            "CANTIDAD"};

    private List<GestionEntity> gestionT;


    public TablaSuministrosPieza(List<GestionEntity> gestriones) {

        gestionT = gestriones;

    }


    @Override
    public int getRowCount() {
        return gestionT.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        GestionEntity i = gestionT.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return i.CodproyectoERP();

            case 1:
                return i.CodprovedorERP();

            case 2:
                return i.getCantidad();


        }//fin switch

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }


}
