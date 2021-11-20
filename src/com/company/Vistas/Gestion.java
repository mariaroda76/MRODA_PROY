package com.company.Vistas;

import com.company.Controllers.GestionController;
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
import java.util.ArrayList;
import java.util.List;

public class Gestion {

    static List<ProveedoresEntity> listaProvedores = new ArrayList();
    static int posicionActual = 0;

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

                            //obtengo el proveedor real de la bD
                            ProveedoresEntity provBD = ProveedorController.selectProveedorByCode(provTemp.getCodigo());

                            //antes de eliminarlo debo comprobar que no tiene gestiones abiertas
                            int cantidadGestionesProveedor = GestionController.selectGestionesByProvId(provBD.getId()).size();
                            if (cantidadGestionesProveedor == 0) {


                                TFGestionNombre.setEnabled(false);
                                TFGestionNombre.setText(provBD.getNombre());

                                TFGestionData1.setEnabled(false);
                                TFGestionData1.setText(provBD.getApellidos());

                                TFGestionDireccion.setEnabled(false);
                                TFGestionDireccion.setText(provBD.getDireccion());



                                //pido confirmacion antes de dar de baja
                                if (DataEntryUtils.confirmDBDelete(provBD.toStringEliminar())) {
                                    session.delete(provBD);
                                    JOptionPane.showMessageDialog(null, "Se ha ELIMINADO correctamente el Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
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
                                    JOptionPane.showMessageDialog(null, "Has declinado ELIMINAR al Proveedor", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                                    );
                                    limpiarJTextFields(JPGestionData);
                                }

                            } else {
                                String texto1 = "EL PROVEEDOR TIENE GESTIONES ABIERTAS!! NO SE PUEDE ELIMINAR. \n PRUEBA DARLO DE BAJA O ELIMINA SUS GESTIONES PREVIAMENTE";
                                JOptionPane.showMessageDialog(null, texto1, "Resultado", JOptionPane.ERROR_MESSAGE
                                );
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

                    listaProvedores = ProveedorController.listaProveedoresState(false);
                    int ultimo = listaProvedores.size();

                    if (listaProvedores.size() > 0) {

                        ProveedoresEntity provTemPrimeraVisualizacion = listaProvedores.get(posicionActual);

                        TFListadoCodigo.setText(provTemPrimeraVisualizacion.getCodigo());
                        TFListadoNombre.setText(provTemPrimeraVisualizacion.getNombre());
                        TFListadoData1.setText(provTemPrimeraVisualizacion.getApellidos());
                        TFListadoDireccion.setText(provTemPrimeraVisualizacion.getDireccion());
                        TFListadoRegAnterior.setText(String.valueOf(posicionActual + 1));
                        TFListadoRegSiguiente.setText(String.valueOf(ultimo));

                        habilitarFlechas();
                    }

                }
            }
        });

        JButtonIrAPrimera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (lbGestionData1.getText() == "APELLIDO") {

                    listaProvedores = ProveedorController.listaProveedoresState(false);
                    posicionActual = 0;
                    int ultimo = listaProvedores.size();
                    ProveedoresEntity provTemPrimeroLista = listaProvedores.get(0);

                    TFListadoCodigo.setText(provTemPrimeroLista.getCodigo());
                    TFListadoNombre.setText(provTemPrimeroLista.getNombre());
                    TFListadoData1.setText(provTemPrimeroLista.getApellidos());
                    TFListadoDireccion.setText(provTemPrimeroLista.getDireccion());
                    TFListadoRegAnterior.setText(String.valueOf(posicionActual + 1));
                    TFListadoRegSiguiente.setText(String.valueOf(ultimo));

                }

            }
        });

        JButtonIrAUltima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lbGestionData1.getText() == "APELLIDO") {

                    listaProvedores = ProveedorController.listaProveedoresState(false);
                    int ultimo = listaProvedores.size();
                    posicionActual = ultimo - 1;
                    ProveedoresEntity provTemUltimo = listaProvedores.get(ultimo - 1);

                    TFListadoCodigo.setText(provTemUltimo.getCodigo());
                    TFListadoNombre.setText(provTemUltimo.getNombre());
                    TFListadoData1.setText(provTemUltimo.getApellidos());
                    TFListadoDireccion.setText(provTemUltimo.getDireccion());
                    TFListadoRegAnterior.setText(String.valueOf(ultimo));
                    TFListadoRegSiguiente.setText(String.valueOf(ultimo));

                }
            }

        });

        JButtonIrAAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lbGestionData1.getText() == "APELLIDO") {

                    listaProvedores = ProveedorController.listaProveedoresState(false);
                    int ultimo = listaProvedores.size();

                    if (posicionActual > 0) {

                        posicionActual = posicionActual - 1;

                        ProveedoresEntity provTemActual = listaProvedores.get(posicionActual);

                        TFListadoCodigo.setText(provTemActual.getCodigo());
                        TFListadoNombre.setText(provTemActual.getNombre());
                        TFListadoData1.setText(provTemActual.getApellidos());
                        TFListadoDireccion.setText(provTemActual.getDireccion());
                        TFListadoRegAnterior.setText(String.valueOf(posicionActual + 1));
                        TFListadoRegSiguiente.setText(String.valueOf(ultimo));
                    }

                }


            }
        });

        JButtonIrASiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lbGestionData1.getText() == "APELLIDO") {

                    listaProvedores = ProveedorController.listaProveedoresState(false);
                    int ultimo = listaProvedores.size();

                    if (posicionActual < ultimo - 1) {

                        posicionActual = posicionActual + 1;

                        ProveedoresEntity provTemActual = listaProvedores.get(posicionActual);

                        TFListadoCodigo.setText(provTemActual.getCodigo());
                        TFListadoNombre.setText(provTemActual.getNombre());
                        TFListadoData1.setText(provTemActual.getApellidos());
                        TFListadoDireccion.setText(provTemActual.getDireccion());
                        TFListadoRegAnterior.setText(String.valueOf(posicionActual + 1));
                        TFListadoRegSiguiente.setText(String.valueOf(ultimo));
                    }

                }


            }
        });

        JButtonBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Inicio sesion
                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                switch (lbGestionData1.getText()) {

                    case "APELLIDO":

                        //selecciono el prov de la bdd
                        ProveedoresEntity provBD = ProveedorController.selectProveedorByCode(TFListadoCodigo.getText());

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
                            posicionActual = 0;
                            limpiarJTextFields(JPListado);
                            bloquerFlechas();
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
                            posicionActual = 0;
                            limpiarJTextFields(JPListado);
                            bloquerFlechas();
                        }

                        break;

                    default:
                        System.out.println("case no implementado aun");

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
        bloquerFlechas();


    }

    public void gestionPieza() {
        //SET GESTION
        this.lbGestionData1.setText("PRECIO");
        this.JPGestionDireccion.setVisible(false);

        //SET LISTADO
        this.lbListado.setText("LISTADO PIEZAS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("PRECIO");
        this.JPListadoDireccion.setVisible(false);
        bloquerFlechas();
    }

    public void gestionProyectos() {
        //SET GESTION
        this.lbGestionData1.setText("CIUDAD");
        this.JPGestionDireccion.setVisible(false);


        //SET LISTADO
        this.lbListado.setText("LISTADO PROYECTOS: UTILIZA BOTONES PARA IR DE UN REGISTRO A OTRO");
        this.lbListadoData1.setText("CIUDAD");
        this.JPListadoDireccion.setVisible(false);
        bloquerFlechas();

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

    private void bloquerFlechas() {
        this.JButtonIrASiguiente.setEnabled(false);
        this.JButtonIrAAnterior.setEnabled(false);
        this.JButtonIrAPrimera.setEnabled(false);
        this.JButtonIrAUltima.setEnabled(false);
        this.JButtonBaja.setEnabled(false);
    }

    private void habilitarFlechas() {
        this.JButtonIrASiguiente.setEnabled(true);
        this.JButtonIrAAnterior.setEnabled(true);
        this.JButtonIrAPrimera.setEnabled(true);
        this.JButtonIrAUltima.setEnabled(true);
        this.JButtonBaja.setEnabled(true);
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

    private ProveedoresEntity getProveedorFromList() {

        ProveedoresEntity prov = new ProveedoresEntity();
        prov.setCodigo(TFListadoCodigo.getText().toUpperCase().trim());
        prov.setNombre(TFListadoNombre.getText().toUpperCase().trim());
        prov.setApellidos(TFListadoData1.getText().toUpperCase().trim());
        prov.setDireccion(TFListadoDireccion.getText().toUpperCase().trim());

        return prov;
    }


}













