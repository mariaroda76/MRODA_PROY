package com.company.Controllers;

import com.company.GestionEntity;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

}



