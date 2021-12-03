package com.company.Vistas;

import com.company.Controllers.GestionController;
import com.company.Utils.TablaPedidos;
import com.company.Utils.TablaSuministrosPieza;
import com.company.Utils.TablaSuministrosProveedor;

import javax.swing.*;

public class Listado {
    private JPanel JPGestionesTodas;
    private JScrollPane JPSPedidos;
    private JTable tablaPedidios;


    public Listado(int opcion,int id) {

        tablaPedidios = new JTable();
        JPSPedidos.setViewportView(tablaPedidios);
        if (opcion==1) {
            tablaPedidios.setModel(new TablaPedidos(GestionController.listaGestionesAll()));
        }else if(opcion == 2){
            tablaPedidios.setModel(new TablaSuministrosProveedor(GestionController.listaSuministroProveedor(id)));
        }
        else if(opcion == 3){
            tablaPedidios.setModel(new TablaSuministrosPieza(GestionController.listaSuministroPieza(id)));
        }

    }

    public JPanel getJPGestionesTodas() {
        return JPGestionesTodas;

    }


    public void autoDestroy() {

        JPGestionesTodas.removeAll();
        JPGestionesTodas.repaint();
    }


}



