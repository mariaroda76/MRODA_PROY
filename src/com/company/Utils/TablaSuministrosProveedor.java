package com.company.Utils;

import com.company.GestionEntity;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablaSuministrosProveedor extends AbstractTableModel {

    private String[] columnas = {"CODIGO PROYECTO",
            "CODIGO PIEZA",
            "CANTIDAD"};

    private List<GestionEntity> gestionT;


    public TablaSuministrosProveedor(List<GestionEntity> gestriones) {

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
                //return i.getCodproyecto();
            case 1:
                return i.CodpiezaERP();
                //return i.getCodpieza();
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
