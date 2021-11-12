package com.company.Vistas;

import javax.swing.*;

public class Gestion{

    private JPanel JPGeneral;
    private JPanel JPVacio;


    private JTabbedPane TPanelGeneral;
    private JPanel JPGestion;
    private JPanel JPListado;
    private JButton JButtonModificar;
    private JButton JButtonEliminar;
    private JButton JButtonInsertar;
    private JButton JButtonLimpiar;
    private JTextField TFGestionCodigo;
    private JTextField TFGestionNombre;
    private JTextField TFGestionData1;
    private JTextField TFGestionDireccion;
    private JPanel JPGestionButtons;
    private JPanel JPGestion_Informacion;
    private JPanel JPGestionData;
    private JPanel JPGestionCodigo;
    private JPanel JPGestionNombre;
    private JPanel JPGestionData1;
    private JPanel JPGestionDireccion;
    private JLabel lbGestionCodigo;
    private JLabel lbGestionDireccion;
    private JLabel lbGestionData1;
    private JLabel lbGestionNombre;
    private JButton JButtonBaja;
    private JButton JButtonConsulta;
    private JButton JButtonIrAPrimera;
    private JButton JButtonIrAAnterior;
    private JButton JButtonIrASiguiente;
    private JButton JButtonIrAUltima;
    private JTextField TFListadoRegAnterior;
    private JTextField TFListadoRegSiguiente;
    private JTextField TFListadoCodigo;
    private JTextField TFListadoNombre;
    private JTextField TFListadoData1;
    private JTextField TFListadoDireccion;
    private JPanel JPListado_Informacion;
    private JLabel lbGestion;
    private JLabel lbListado;
    private JPanel JPListadoData;
    private JPanel JPListadoCodigo;
    private JPanel JPListadoNombre;
    private JPanel JPListadoData1;
    private JPanel JPListadoDireccion;
    private JPanel JPListadoegistros;
    private JLabel lbListadoCodigo;
    private JLabel lbListadoNombre;
    private JLabel lbListadoData1;
    private JLabel lbListadoDireccion;
    private JLabel lbListadoRegs;
    private JPanel JPListadoButtons;
    private JPanel JPListadoBtnsDerecha;
    private JPanel JPListadoBtnsIzq;
    private JPanel JPListadoBtnsIzqArriba;
    private JPanel JPListadoBtnsIzqAbajo;



    public Gestion() {

    }



    /**
     * Esta funcion nos permite reutilizar el frame ycambiar la parte inferior donde aparecen las pantallas degestion
     * empleado, espectaculo....
     */
    public void mostrarPanel(JPanel panel) {

        JPVacio.removeAll();
        JPVacio.add(panel);
        JPVacio.repaint();
        JPVacio.revalidate();

    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }


    public JPanel getJPVacio() {
        return JPVacio;
    }

    public void gestionProveedor(){

        //SET GESTION
        this.lbGestionData1.setText("APELLIDO");

        //SET LISTADO
        this.lbListado.setText("LISTADO PROVEEDORES: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("APELLIDO");

    }

    public void gestionPieza(){
        //SET GESTION
        this.lbGestionData1.setText("PRECIO");
        this.JPGestionDireccion.setVisible(false);

        //SET LISTADO
        this.lbListado.setText("LISTADO PIEZAS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("PRECIO");
        this.JPListadoDireccion.setVisible(false);

    }

    public void gestionProyectos(){
        //SET GESTION
        this.lbGestionData1.setText("CIUDAD");
        this.JPGestionDireccion.setVisible(false);


        //SET LISTADO
        this.lbListado.setText("LISTADO PROYECTOS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("CIUDAD");
        this.JPListadoDireccion.setVisible(false);

    }




}
