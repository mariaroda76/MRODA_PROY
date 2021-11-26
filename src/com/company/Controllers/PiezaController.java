package com.company.Controllers;

import com.company.PiezasEntity;
import com.company.Utils.ChecksUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class PiezaController {

    public static List<PiezasEntity> listaPiezasAll() {


        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from PiezasEntity ");
        List<PiezasEntity> lista = q.list();


        session.close();


        return lista;
    }

    public static List<PiezasEntity> listaPiezasState(boolean baja) {

        List<PiezasEntity> listaPiezas = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from PiezasEntity ");
        List<PiezasEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<PiezasEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            PiezasEntity proy = (PiezasEntity) iter.next();
            System.out.println("Pieza: " + proy.toString());
            if (proy.getBaja() == baja) {
                listaPiezas.add(proy);
            }
        }
        session.close();


        return listaPiezas;
    }

    public static List<String> listaCodigosPiezas() {

        List listaCodigos = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from PiezasEntity ");
        List<PiezasEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<PiezasEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            PiezasEntity proy = (PiezasEntity) iter.next();
            listaCodigos.add(proy.getCodigo().toUpperCase());
        }
        session.close();


        return listaCodigos;
    }

    public static List<String> listaIdPiezas() {

        List listaIds = new ArrayList();

        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from PiezasEntity ");
        List<PiezasEntity> lista = q.list();

        // Obtenemos un Iterador y recorremos la lista
        Iterator<PiezasEntity> iter = lista.iterator();

        while (iter.hasNext()) {
            //extraer el objeto
            PiezasEntity proy = (PiezasEntity) iter.next();
            listaIds.add(proy.getId());
        }
        session.close();


        return listaIds;
    }

    public static String validaciones(PiezasEntity Pieza, int tipoAccion) {

        HashMap<String, String> errores = new HashMap<>();
        List listaCodigos = listaCodigosPiezas();

        if (tipoAccion == 0) { //si es insert=0 NO DEBE EXISTIR CODIGO
            if (ChecksUtils.existeCodigo(listaCodigos, Pieza.getCodigo())) {
                errores.put("CODIGO", "YA EXISTE UNA PIEZA CON EL CODIGO INSERTADO");
            }
        } else if (tipoAccion == 1) { //si es modificar= 1 DEBE EXISTIR CODIGO

            if (!ChecksUtils.existeCodigo(listaCodigos, Pieza.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PIEZA NO EXISTE");
            }
        } else if (tipoAccion == 2) {//si es baja= 2 DEBE EXISTIR CODIGO y que el codigo no pertenezca a un Pieza de baja
            if (!ChecksUtils.existeCodigo(listaCodigos, Pieza.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PIEZA NO EXISTE");
            }
        }

        if (Pieza.getCodigo().length() > 6 || Pieza.getCodigo().equals("")) {
            if (Pieza.getCodigo().length() > 6) {
                errores.put("CODIGO", "EL CODIGO EXCEDE LA LONGITUD MAXIMA DE 6 CARACTERES");
            } else {
                errores.put("CODIGO", "EL CODIGO NO PUEDE ESTAR VACIO");
            }
        }

        if (tipoAccion != 2) { // si no es eliminar necesito todos los datos en el form

            if (Pieza.getNombre().length() > 40 || Pieza.getNombre().equals("")) {
                if (Pieza.getNombre().length() > 40) {
                    errores.put("NOMBRE", "EL NOMBRE EXCEDE LA LONGITUD MAXIMA DE 40 CARACTERES");
                } else {
                    errores.put("NOMBRE", "EL NOMBRE NO PUEDE ESTAR VACIO");
                }
            }

            if (Pieza.getPrecio() <0){
                errores.put("PRECIO", "EL PRECIO DEBE SER MAYOR QUE CERO");
            }




            if (Pieza.getDescripcion().equals("")) {
                    errores.put("DESCRIPCION", "LA DESCRIPCION NO PUEDE ESTAR VACIA");
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

    public static PiezasEntity selectPiezaByCode(String codigo) {

        List listaCodigos = listaCodigosPiezas();
        List listaPiezas = listaPiezasAll();

        // si la lista de codigos contiene el codigo que le paso...
        if (ChecksUtils.existeCodigo(listaCodigos, codigo)) {
            //recorro los PiezaS y me quedo con el que tiene el codigo que le he pasado
            for (int i = 0; i < listaPiezas.size(); i++) {
                if (((PiezasEntity) listaPiezas.get(i)).getCodigo().equals(codigo)) {
                    return ((PiezasEntity) listaPiezas.get(i));
                }
            }
        }
        return null;
    }

    public static PiezasEntity selectPiezaById(int id) {

        List listaIds = listaIdPiezas();
        List listaPiezas = listaPiezasAll();

        // si la lista de codigos contiene el codigo que le paso...
        if (ChecksUtils.existeId(listaIds, id)) {
            //recorro los porveedores y me quedo con el que tiene el id que le he pasado
            for (int i = 0; i < listaPiezas.size(); i++) {
                if (((PiezasEntity) listaPiezas.get(i)).getId() == id) {
                    return ((PiezasEntity) listaPiezas.get(i));
                }
            }
        }
        return null;
    }
    
}
