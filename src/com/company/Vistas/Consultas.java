package com.company.Vistas;

import javax.swing.*;

public class Consultas {
    private JPanel JPGeneral;
    private JPanel JPVacio;
    private JTextField TFConsultaBusqueda;
    private JButton JButtonConsultaBuscar;
    private JComboBox JComboConsultaResultado;
    private JPanel JPConsultasBusqueda;
    private JPanel JPConsultasCombo;
    private JPanel JPConsultasData;
    private JLabel lbConsultaBusqueda;
    private JPanel JPConsultaCodigo;
    private JPanel JPConsultaNombre;
    private JPanel JPConsultaData1;
    private JPanel JPConsultaDireccion;
    private JLabel lbConsultaFixCodigo;
    private JLabel lbConsultaFixNombre;
    private JLabel lbConsultaFixData1;
    private JLabel lbConsultaFixDireccion;
    private JLabel lbConsultaResultCodigo;
    private JLabel lbConsultaResultNombre;
    private JLabel lbConsultaResultData1;
    private JLabel lbConsultaResultDireccion;

    public Consultas() {
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


    public void ProveedorPorCodigo(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("APELLIDO");

    }

    public void ProveedorPorNombre(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("APELLIDO");


    }

    public void ProveedorPorDireccion(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe DIRECCION o parte de la misma");
        this.lbConsultaFixData1.setText("APELLIDO");


    }

    ///////////POR PIEZA

    public void PiezaPorCodigo(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("PRECIO");
        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);

    }

    public void PiezaPorNombre(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("PRECIO");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }

    ///////////POR PROYECTO

    public void ProyectoPorCodigo(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);

    }

    public void ProyectoPorNombre(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }

    public void ProyectoPorCiudad(){

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe DIRECCION o parte de la misma");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }




}
