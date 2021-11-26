package com.company.Controllers;

import com.company.GestionEntity;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class GestionController {


    public static List<GestionEntity> listaGestionesAll() {

        List<GestionEntity> listaGestiones = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from GestionEntity ");
        List<GestionEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<GestionEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            GestionEntity gest = (GestionEntity) iter.next();
            System.out.println("proveedor: " + gest.toString());
            listaGestiones.add(gest);
        }
        session.close();


        return listaGestiones;
    }

    public static List<GestionEntity> selectGestionesByProvId(int id) {

        List<GestionEntity> listaPorProv = new ArrayList<>();
        List listaGestiones = listaGestionesAll();

        // si la lista de codigos contiene el id que le paso...
        //recorro los porveedores y me quedo con el que tiene el id que le he pasado
        for (int i = 0; i < listaGestiones.size(); i++) {
            int idTemp = ((GestionEntity) listaGestiones.get(i)).getCodproveedor();

            if (idTemp == id) {
                listaPorProv.add((GestionEntity) listaGestiones.get(i));
            }
        }
        return listaPorProv;
    }

    public static List<GestionEntity> selectGestionesByProyId(int id) {

        List<GestionEntity> listaPorProy = new ArrayList<>();
        List listaGestiones = listaGestionesAll();

        // si la lista de codigos contiene el id que le paso...
        //recorro los porveedores y me quedo con el que tiene el id que le he pasado
        for (int i = 0; i < listaGestiones.size(); i++) {
            int idTemp = ((GestionEntity) listaGestiones.get(i)).getCodproyecto();

            if (idTemp == id) {
                listaPorProy.add((GestionEntity) listaGestiones.get(i));
            }
        }
        return listaPorProy;
    }

    public static List<GestionEntity> selectGestionesByPiezaId(int id) {

        List<GestionEntity> listaPorPieza = new ArrayList<>();
        List listaGestiones = listaGestionesAll();

        // si la lista de codigos contiene el id que le paso...
        //recorro las piezasy me quedo con el que tiene el id que le he pasado
        for (int i = 0; i < listaGestiones.size(); i++) {
            int idTemp = ((GestionEntity) listaGestiones.get(i)).getCodpieza();

            if (idTemp == id) {
                listaPorPieza.add((GestionEntity) listaGestiones.get(i));
            }
        }
        return listaPorPieza;
    }


    public static String validaciones(GestionEntity pedido, int tipoAccion) {

        HashMap<String, String> errores = new HashMap<>();

        List listagestiones = listaGestionesAll();
        int codPieza = pedido.getCodpieza();
        int codProve = pedido.getCodproveedor();
        int codProy = pedido.getCodproyecto();

        if (tipoAccion == 0) { //si es insert=0 NO DEBE EXISTIR COMBINACION DE CODIGOS

            for (int i = 0; i < listagestiones.size(); i++) {
                GestionEntity g = (GestionEntity) listagestiones.get(i);
                if ((g.getCodpieza() == codPieza) && (g.getCodproveedor() == codProve) && (g.getCodproyecto() == codProy)) {
                    errores.put("CODIGOS", "YA EXISTE UN PEDIDO DE ESA PIEZA, DE ESE PROVEEDOR PARA ESE PROYECTO");
                }
            }

            if (codPieza == -1) {
                errores.put("PIEZA", "EL CODIGO DE LA PIEZA NO PUEDE QUEDAR VACIO");
            }
            if (codProve == -1) {
                errores.put("PROVEEDOR", "EL CODIGO DEL PROVEEDOR NO PUEDE QUEDAR VACIO");
            }
            if (codProy == -1) {
                errores.put("PROYECTO", "EL CODIGO DEL PROYECTO NO PUEDE QUEDAR VACIO");
            }
            if (pedido.getCantidad() < 0) {
                errores.put("CANTIDAD", "LA CANTIDAD DEBE SER MAYOR A CERO");
            }
        } else if (tipoAccion == 1) { //si es modificar= 1 DEBE EXISTIR ALGUN CAMBIO

            boolean encontrado = false;

            for (int i = 0; i < listagestiones.size(); i++) {
                GestionEntity g = (GestionEntity) listagestiones.get(i);
                if ((g.getCodpieza() == codPieza) && (g.getCodproveedor() == codProve) && (g.getCodproyecto() == codProy)) {
                    encontrado = true;

                }
            }
            if (!encontrado) {
                errores.put("CODIGOS", "NO EXISTE PEDIDO DE ESA PIEZA, DE ESE PROVEEDOR PARA ESE PROYECTO");
            }

            if (codPieza == -1) {
                errores.put("PIEZA", "EL CODIGO DE LA PIEZA NO PUEDE QUEDAR VACIO");
            }
            if (codProve == -1) {
                errores.put("PROVEEDOR", "EL CODIGO DEL PROVEEDOR NO PUEDE QUEDAR VACIO");
            }
            if (codProy == -1) {
                errores.put("PROYECTO", "EL CODIGO DEL PROYECTO NO PUEDE QUEDAR VACIO");
            }
            if (pedido.getCantidad() < 0) {
                errores.put("CANTIDAD", "LA CANTIDAD DEBE SER MAYOR A CERO");
            }


        } else if (tipoAccion == 2) {//si es baja= 2 DEBE EXISTIR LA COMBINACION DE LOS 3 PARA QUE SE ELIMINE

            boolean encontrado = false;

            for (int i = 0; i < listagestiones.size(); i++) {
                GestionEntity g = (GestionEntity) listagestiones.get(i);
                if ((g.getCodpieza() == codPieza) && (g.getCodproveedor() == codProve) && (g.getCodproyecto() == codProy)) {
                    encontrado = true;
                }
            }
            if (!encontrado) {
                errores.put("CODIGOS", "NO EXISTE PEDIDO DE ESA PIEZA, DE ESE PROVEEDOR PARA ESE PROYECTO");
            }
            if (codPieza == -1) {
                errores.put("PIEZA", "EL CODIGO DE LA PIEZA NO PUEDE QUEDAR VACIO");
            }
            if (codProve == -1) {
                errores.put("PROVEEDOR", "EL CODIGO DEL PROVEEDOR NO PUEDE QUEDAR VACIO");
            }
            if (codProy == -1) {
                errores.put("PROYECTO", "EL CODIGO DEL PROYECTO NO PUEDE QUEDAR VACIO");
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



