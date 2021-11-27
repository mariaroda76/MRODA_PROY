package com.company.Vistas;

import com.company.Controllers.GestionController;
import com.company.Utils.TablaPedidos;

import javax.swing.*;

public class Listado {
    private JPanel JPGestionesTodas;
    private JScrollPane JPSPedidos;
    private JTable tablaPedidios;


    public Listado() {

        tablaPedidios = new JTable();
        JPSPedidos.setViewportView(tablaPedidios);
        tablaPedidios.setModel(new TablaPedidos(GestionController.listaGestionesAll()));

    }

    public JPanel getJPGestionesTodas() {
        return JPGestionesTodas;

    }


    public void autoDestroy() {

        JPGestionesTodas.removeAll();
        JPGestionesTodas.repaint();
    }


}



