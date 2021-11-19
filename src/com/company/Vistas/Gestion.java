package com.company.Vistas;

import com.company.ProveedoresEntity;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        JButtonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                if (lbGestionData1.getText() == "APELLIDO") {

                    ProveedoresEntity prov = new ProveedoresEntity();
                    prov = getProveedorFromForm();

                    session.save(prov);
                    try {
                        tx.commit();
                    } catch (ConstraintViolationException err) {
                        System.out.println("PROVEEDOR DUPLICADO");
                        System.out.printf("MENSAJE:%s%n", err.getMessage());
                        System.out.printf("COD ERROR:%d%n", err.getErrorCode());
                        System.out.printf("ERROR SQL:%s%n", err.getSQLException().getMessage());
                    }

                    session.close();

                }


            }
        });


        JButtonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lbGestionData1.getText() == "APELLIDO") {

                    listaProveedores();
                }
            }
        });


    }

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


    private ProveedoresEntity getProveedorFromForm() {
        ProveedoresEntity prov = new ProveedoresEntity();

        prov.setCodigo(TFGestionCodigo.getText().trim());
        prov.setNombre(TFGestionNombre.getText().trim());
        prov.setApellidos(TFGestionData1.getText().trim());
        prov.setDireccion(TFGestionDireccion.getText().trim());
        return prov;
    }

    private List<ProveedoresEntity> listaProveedores() {

        List listaProvedores = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProveedoresEntity ");
        List<ProveedoresEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<ProveedoresEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            ProveedoresEntity prov = (ProveedoresEntity) iter.next();
            System.out.println("proveedor: " + prov.getCodigo());
            listaProvedores.add(prov);
        }
        session.close();


        return listaProvedores;
    }

    private List<String> listaCodigosProvedores() {

        List listaCodigos = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProveedoresEntity ");
        List<ProveedoresEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<ProveedoresEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            ProveedoresEntity prov = (ProveedoresEntity) iter.next();
            listaCodigos.add(prov.getCodigo());
        }
        session.close();


        return listaCodigos;
    }

    private Boolean existeCodigo(List lista, String codigo) {
        if (lista.contains(codigo)) {
            return true;
        }
        return false;
    }


    public String validaciones(ProveedoresEntity proveedor) {

        HashMap<String, String> errores = new HashMap<>();
        List listaCodigos = listaCodigosProvedores();

        if (listaCodigos.contains(proveedor.getCodigo())) {
            errores.put("CODIGO", "YA EXISTE UN PROVEEDOR CON EL CODIGO INSERTADO");
        } else {
            if (proveedor.getCodigo().length() > 6 || proveedor.getCodigo().equals("")) {
                if (proveedor.getCodigo().length() > 6) {
                    errores.put("CODIGO", "EL CODIGO EXCEDE LA LONGITUD MAXIMA DE 6 CARACTERES");
                } else {
                    errores.put("CODIGO", "EL CODIGO NO PUEDE ESTAR VACIO");
                }
            }
        }

            if (proveedor.getNombre().length() > 20 || proveedor.getNombre().equals("")) {
                if (proveedor.getNombre().length() > 20) {
                    errores.put("NOMBRE", "EL NOMBRE EXCEDE LA LONGITUD MAXIMA DE 20 CARACTERES");
                } else {
                    errores.put("NOMBRE", "EL NOMBRE NO PUEDE ESTAR VACIO");
                }
            }


            //Utilizamos esta variable para guardar el mensaje de error.
            StringBuilder texto = new StringBuilder();

            if (errores.size() > 0) {
                for (Map.Entry<String, String> entry : errores.entrySet()) {
                    String k = entry.getKey();
                    String v = entry.getValue();
                    texto.append(v + "\n");
                }
                return texto.toString();
            } else {
                return null;
            }
        }


    }











