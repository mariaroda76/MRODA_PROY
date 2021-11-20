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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
                        if (ProveedorController.validaciones(prov, 0) == null) {

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
                            String texto = ProveedorController.validaciones(prov, 0);
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
                        //recojo la info del panel e instancio un proveedor temporal
                        ProveedoresEntity provTemp = getProveedorFromForm();

                        //compruebo los datos del proveedor temporal  antes de intentar modificar en la BDD
                        if (ProveedorController.validaciones(provTemp, 1) == null) {

                            ProveedoresEntity provBD = ProveedorController.selectProveedorByCode(provTemp.getCodigo());

                            provBD.setNombre(provTemp.getNombre());
                            provBD.setApellidos(provTemp.getApellidos());
                            provBD.setDireccion(provTemp.getDireccion());
                            //provBD.setBaja(provTemp.getBaja());
                            //provBD.setFechabaja(provTemp.getFechabaja());

                            //pido confirmacion antes de MODIFICAR
                            if (DataEntryUtils.confirmDBUpdate(provBD.toString())) {
                                session.update(provBD);
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
                            String texto = ProveedorController.validaciones(provTemp, 1);
                            JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                            );
                        }
                        break;

                    default:
                        System.out.println("case no implementado aun");

                }
            }
        });

        JButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Inicio sesion
                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                switch (lbGestionData1.getText()) {

                    case "APELLIDO":
                        //recojo la info del panel e instancio un proveedor temporal
                        ProveedoresEntity provTemp = getProveedorFromForm();

                        //compruebo los datos del proveedor temporal  antes de intentar DARLE DE BAJA en la BDD
                        if (ProveedorController.validaciones(provTemp, 2) == null) {

                            ProveedoresEntity provBD = ProveedorController.selectProveedorByCode(provTemp.getCodigo());

                            //solo me interesa el codigo que han puesto en el field, el resto de datos no serian validos para hacer una baja
                            //por eso los seteo todos segun el objeto de la BD

                            TFGestionNombre.setEnabled(false);
                            TFGestionNombre.setText(provBD.getNombre());

                            TFGestionData1.setEnabled(false);
                            TFGestionData1.setText(provBD.getApellidos());

                            TFGestionDireccion.setEnabled(false);
                            TFGestionDireccion.setText(provBD.getDireccion());

                            //le doy de baja
                            provBD.setBaja(true);

                            //guardo la fecha de hoy como fecha de la baja: la guardo como string
                            java.util.Date date = new java.util.Date();
                            DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
                            String ahora = fechaHora.format(date);

                            provBD.setFechabaja(ahora.toUpperCase());


                            //pido confirmacion antes de dar de baja
                            if (DataEntryUtils.confirmDBBaja(provBD.toStringBaja())) {
                                session.update(provBD);
                                JOptionPane.showMessageDialog(null, "Se ha DADO DE BAJA correctamente al Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                                limpiarJTextFields(JPGestionData);
                                try {
                                    tx.commit();
                                } catch (Exception e1) {
                                    System.out.println("ERROR NO CONTROLADO");
                                    System.out.printf("MENSAJE:%s%n", e1.getMessage());
                                }
                                limpiarJTextFields(getJPGeneral());
                                session.close();
                            } else {
                                JOptionPane.showMessageDialog(null, "Has declinado DAR DE BAJA al Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                );
                                limpiarJTextFields(JPGestionData);
                            }
                        } else {
                            //En este string guardamos todos los errores, y lo mostramos.
                            String texto = ProveedorController.validaciones(provTemp, 2);
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
                component.setEnabled(true);
            } else if (component instanceof JPanel) {
                Container mypanel = (Container) component;
                limpiarJTextFields(mypanel);
            }
        }

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













