package com.company.Vistas;

import com.company.Controllers.ProveedorController;
import com.company.ProveedoresEntity;
import com.company.Utils.DataEntryUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gestion {


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

        //tab gestion
        JButtonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Inicio sesion
                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                switch (lbGestionData1.getText()) {

                    case "APELLIDO":
                        //recojo la info del panel e instancio un proveedor
                        ProveedoresEntity prov = getProveedorFromForm();

                        //compruebo los datos del proveedor  antes de intentar subirlos a la BDD
                        if (ProveedorController.validaciones(prov, true) == null) {

                            //pido confirmacion antes de guardar
                            if (DataEntryUtils.confirmDBSave(prov.toString())) {
                                session.save(prov);
                                JOptionPane.showMessageDialog(null, "Se ha INSERTADO correctamente el nuevo Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                                limpiarJTextFields(JPGestionData);
                                try {
                                    tx.commit();
                                } catch (Exception e1) {
                                    System.out.println("ERROR NO CONTROLADO");
                                    System.out.printf("MENSAJE:%s%n", e1.getMessage());
                                }
                                session.close();
                            } else {
                                JOptionPane.showMessageDialog(null, "Has declinado insertar un nuevo Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } else {
                            //En este string guardamos todos los errores, y lo mostramos.
                            String texto = ProveedorController.validaciones(prov, true);
                            JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                            );
                        }
                        break;

                    default:
                        System.out.println("case no implementado aun");

                }
            }
        });

        JButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Inicio sesion
                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                switch (lbGestionData1.getText()) {

                    case "APELLIDO":
                        //recojo la info del panel e instancio un proveedor
                        ProveedoresEntity prov = getProveedorFromForm();

                        //compruebo los datos del proveedor  antes de intentar modificarlos a la BDD
                        if (ProveedorController.validaciones(prov, false) == null) {

                            //pido confirmacion antes de MODIFICAR
                            if (DataEntryUtils.confirmDBUpdate(prov.toString())) {
                                session.update(prov);
                                JOptionPane.showMessageDialog(null, "Se ha MODIFICADO correctamente el Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                                limpiarJTextFields(JPGestionData);
                                try {
                                    tx.commit();
                                } catch (Exception e1) {
                                    System.out.println("ERROR NO CONTROLADO");
                                    System.out.printf("MENSAJE:%s%n", e1.getMessage());
                                }
                                session.close();
                            } else {
                                JOptionPane.showMessageDialog(null, "Has declinado MODIFICAR el Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } else {
                            //En este string guardamos todos los errores, y lo mostramos.
                            String texto = ProveedorController.validaciones(prov, false);
                            JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                            );
                        }
                        break;

                    default:
                        System.out.println("case no implementado aun");

                }
            }
        });


        JButtonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                limpiarJTextFields(JPGestionData);

            }
        });

        //tab listado
        JButtonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lbGestionData1.getText() == "APELLIDO") {

                    ProveedorController.listaProveedores();
                }
            }
        });

    }

    //METODOS DE VISTAS
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

    public void gestionProveedor() {

        //SET GESTION
        this.lbGestionData1.setText("APELLIDO");

        //SET LISTADO
        this.lbListado.setText("LISTADO PROVEEDORES: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("APELLIDO");

    }

    public void gestionPieza() {
        //SET GESTION
        this.lbGestionData1.setText("PRECIO");
        this.JPGestionDireccion.setVisible(false);

        //SET LISTADO
        this.lbListado.setText("LISTADO PIEZAS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("PRECIO");
        this.JPListadoDireccion.setVisible(false);

    }

    public void gestionProyectos() {
        //SET GESTION
        this.lbGestionData1.setText("CIUDAD");
        this.JPGestionDireccion.setVisible(false);


        //SET LISTADO
        this.lbListado.setText("LISTADO PROYECTOS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("CIUDAD");
        this.JPListadoDireccion.setVisible(false);

    }

    private static void limpiarJTextFields(Container container) {

        Component[] mycomponents = container.getComponents();

        for (Component component : mycomponents) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JPanel) {
                Container mypanel = (Container) component;
                limpiarJTextFields(mypanel);
            }
        }
/*
        // New-style with Stream
        Stream.of(container.getComponents())
                .filter(c -> c instanceof JTextField)
                .map(c -> ((JTextField) c).getText())
                .forEach(System.out::println);*/
    }


    //METODOS DE ENTITIES
    private ProveedoresEntity getProveedorFromForm() {
        ProveedoresEntity prov = new ProveedoresEntity();
        prov.setCodigo(TFGestionCodigo.getText().toUpperCase().trim());
        prov.setNombre(TFGestionNombre.getText().toUpperCase().trim());
        prov.setApellidos(TFGestionData1.getText().toUpperCase().trim());
        prov.setDireccion(TFGestionDireccion.getText().toUpperCase().trim());
        return prov;
    }



}













