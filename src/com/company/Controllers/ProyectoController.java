package com.company.Controllers;

import com.company.ProyectosEntity;
import com.company.Utils.ChecksUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class ProyectoController {

    public static List<ProyectosEntity> listaProyectosAll() {


        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProyectosEntity ");
        List<ProyectosEntity> lista = q.list();


        session.close();


        return lista;
    }

    public static List<ProyectosEntity> listaProyectosState(boolean baja) {

        List<ProyectosEntity> listaProyectos = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProyectosEntity ");
        List<ProyectosEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<ProyectosEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            ProyectosEntity proy = (ProyectosEntity) iter.next();
            System.out.println("proyecto: " + proy.toString());
            if (proy.getBaja() == baja) {
                listaProyectos.add(proy);
            }
        }
        session.close();


        return listaProyectos;
    }

    public static List<String> listaCodigosProyectos() {

        List listaCodigos = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProyectosEntity ");
        List<ProyectosEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<ProyectosEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            ProyectosEntity proy = (ProyectosEntity) iter.next();
            listaCodigos.add(proy.getCodigo().toUpperCase());
        }
        session.close();


        return listaCodigos;
    }

    public static List<String> listaIdProyectos() {

        List listaIds = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProyectosEntity ");
        List<ProyectosEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<ProyectosEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            ProyectosEntity proy = (ProyectosEntity) iter.next();
            listaIds.add(proy.getId());
        }
        session.close();


        return listaIds;
    }

    public static String validaciones(ProyectosEntity proyecto, int tipoAccion) {

        HashMap<String, String> errores = new HashMap<>();
        List listaCodigos = listaCodigosProyectos();

        if (tipoAccion == 0) { //si es insert=0 NO DEBE EXISTIR CODIGO
            if (ChecksUtils.existeCodigo(listaCodigos, proyecto.getCodigo())) {
                errores.put("CODIGO", "YA EXISTE UN PROYECTO CON EL CODIGO INSERTADO");
            }
        } else if (tipoAccion == 1) { //si es modificar= 1 DEBE EXISTIR CODIGO

            if (!ChecksUtils.existeCodigo(listaCodigos, proyecto.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PROYECTO NO EXISTE");
            }
        } else if (tipoAccion == 2) {//si es baja= 2 DEBE EXISTIR CODIGO y que el codigo no pertenezca a un proyecto de baja
            if (!ChecksUtils.existeCodigo(listaCodigos, proyecto.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PROYECTO NO EXISTE");
            }
        }

        if (proyecto.getCodigo().length() > 6 || proyecto.getCodigo().equals("")) {
            if (proyecto.getCodigo().length() > 6) {
                errores.put("CODIGO", "EL CODIGO EXCEDE LA LONGITUD MAXIMA DE 6 CARACTERES");
            } else {
                errores.put("CODIGO", "EL CODIGO NO PUEDE ESTAR VACIO");
            }
        }

        if (tipoAccion != 2) { // si no es eliminar necesito todos los datos en el form

            if (proyecto.getNombre().length() > 40 || proyecto.getNombre().equals("")) {
                if (proyecto.getNombre().length() > 40) {
                    errores.put("NOMBRE", "EL NOMBRE EXCEDE LA LONGITUD MAXIMA DE 40 CARACTERES");
                } else {
                    errores.put("NOMBRE", "EL NOMBRE NO PUEDE ESTAR VACIO");
                }
            }

            if (proyecto.getCiudad().length() > 40 || proyecto.getCiudad().equals("")) {
                if (proyecto.getCiudad().length() > 40) {
                    errores.put("CIUDAD", "LA CIUDAD EXCEDE LA LONGITUD MAXIMA DE 40 CARACTERES");
                } else {
                    errores.put("CIUDAD", "LA CIUDAD NO PUEDE ESTAR VACIA");
                }
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

    public static ProyectosEntity selectproyectoByCode(String codigo) {

        List listaCodigos = listaCodigosProyectos();
        List listaProyectos = listaProyectosAll();

        // si la lista de codigos contiene el codigo que le paso...
        if (ChecksUtils.existeCodigo(listaCodigos, codigo)) {
            //recorro los PROYECTOS y me quedo con el que tiene el codigo que le he pasado
            for (int i = 0; i < listaProyectos.size(); i++) {
                if (((ProyectosEntity) listaProyectos.get(i)).getCodigo().equals(codigo)) {
                    return ((ProyectosEntity) listaProyectos.get(i));
                }
            }
        }
        return null;
    }

    public static ProyectosEntity selectproyectoById(int id) {

        List listaIds = listaIdProyectos();
        List listaProyectos = listaProyectosAll();

        // si la lista de codigos contiene el codigo que le paso...
        if (ChecksUtils.existeId(listaIds, id)) {
            //recorro los porveedores y me quedo con el que tiene el id que le he pasado
            for (int i = 0; i < listaProyectos.size(); i++) {
                if (((ProyectosEntity) listaProyectos.get(i)).getId() == id) {
                    return ((ProyectosEntity) listaProyectos.get(i));
                }
            }
        }
        return null;
    }
    
    
}
