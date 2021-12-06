package com.company.Vistas;

import com.company.Controllers.GestionController;
import com.company.Utils.TablaPedidos;
import com.company.Utils.TablaSuministrosPieza;
import com.company.Utils.TablaSuministrosProveedor;

import javax.swing.*;

public class Listado {
    private JPanel JPGestionesTodas;
    private JScrollPane JPSPedidos;
    private JPanel JPListadoDataTotales;
    private JPanel JPLProy;
    private JPanel JPLData;
    private JPanel JPLCant;
    private JPanel JPLPrLab;
    private JPanel JPLPrresult;
    private JPanel JPLdatLab;
    private JPanel JPLdatResul;
    private JPanel JPLCanTResult;
    private JPanel JPLCanTLab;
    private JLabel JLcantProy;
    private JLabel JLcantData;
    private JLabel JPcantTot;
    private JLabel JRESpROY;
    private JLabel JRESdATA;
    private JLabel JREStOTS;
    private JTable tablaPedidios;


    public Listado(int opcion,int id) {

        tablaPedidios = new JTable();
        JPSPedidos.setViewportView(tablaPedidios);
        if (opcion==1) {
            tablaPedidios.setModel(new TablaPedidos(GestionController.listaGestionesAll()));
            JPListadoDataTotales.setVisible(false);
        }else if(opcion == 2){
            tablaPedidios.setModel(new TablaSuministrosProveedor(GestionController.listaSuministroProveedor(id)));
            JPListadoDataTotales.setVisible(true);
            JLcantData.setText("CANT PIEZAS:");
            JRESpROY.setText(String.valueOf(GestionController.cantidadProyectosdeProveedor(id)));
            JRESdATA.setText(String.valueOf(GestionController.cantidadtipoPiezasdeProveedor(id)));
            JREStOTS.setText(String.valueOf(GestionController.cantidadTotalSuministrosdeProveedor(id)));
        }
        else if(opcion == 3){
            tablaPedidios.setModel(new TablaSuministrosPieza(GestionController.listaSuministroPieza(id)));
            JPListadoDataTotales.setVisible(true);
            JLcantData.setText("CANT PROOV:");
            JRESpROY.setText(String.valueOf(GestionController.cantidadProyectosdePieza(id)));
            JRESdATA.setText(String.valueOf(GestionController.cantidadProveedoresdePieza(id)));
            JREStOTS.setText(String.valueOf(GestionController.cantidadTotalSuministrosdePieza(id)));

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



