package com.company.Controllers;

import com.company.ProveedoresEntity;
import com.company.Utils.ChecksUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class ProveedorController {


    public static List<ProveedoresEntity> listaProveedoresAll() {



        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from ProveedoresEntity ");
        List<ProveedoresEntity> lista = q.list();


        session.close();


        return lista;
    }

    public static List<ProveedoresEntity> listaProveedoresState(boolean baja) {

        List<ProveedoresEntity> listaProvedores = new ArrayList();

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
            System.out.println("proveedor: " + prov.toString());
            if (prov.getBaja() == baja) {
                listaProvedores.add(prov);
            }
        }
        session.close();


        return listaProvedores;
    }

    public static List<String> listaCodigosProvedores() {

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
            listaCodigos.add(prov.getCodigo().toUpperCase());
        }
        session.close();


        return listaCodigos;
    }

    public static String validaciones(ProveedoresEntity proveedor, int tipoAccion) {

        HashMap<String, String> errores = new HashMap<>();
        List listaCodigos = listaCodigosProvedores();

        if (tipoAccion == 0) { //si es insert=0 NO DEBE EXISTIR CODIGO
            if (ChecksUtils.existeCodigo(listaCodigos, proveedor.getCodigo())) {
                errores.put("CODIGO", "YA EXISTE UN PROVEEDOR CON EL CODIGO INSERTADO");
            }
        } else if (tipoAccion == 1) { //si es modificar= 1 DEBE EXISTIR CODIGO

            if (!ChecksUtils.existeCodigo(listaCodigos, proveedor.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PROVEEDOR NO EXISTE");
            }
        } else if (tipoAccion == 2) {//si es baja= 2 DEBE EXISTIR CODIGO y que el codigo no pertenezca a un proveedor de baja
            if (!ChecksUtils.existeCodigo(listaCodigos, proveedor.getCodigo())) {
                errores.put("CODIGO", "EL CODIGO DE PROVEEDOR NO EXISTE");
            } /*else {
                if (proveedor.getBaja()) {
                    errores.put("BAJA", "EL CODIGO INTRODUCIDO PERTENECE A UN PROVEEDOR QUE YA HA SIDO DADO DE BAJA");
                }
            }*/
        }


        if (tipoAccion != 2 ) { // si no es eliminar necesito todos los datos en el form

            if (proveedor.getCodigo().length() > 6 || proveedor.getCodigo().equals("")) {
                if (proveedor.getCodigo().length() > 6) {
                    errores.put("CODIGO", "EL CODIGO EXCEDE LA LONGITUD MAXIMA DE 6 CARACTERES");
                } else {
                    errores.put("CODIGO", "EL CODIGO NO PUEDE ESTAR VACIO");
                }
            }


            if (proveedor.getNombre().length() > 20 || proveedor.getNombre().equals("")) {
                if (proveedor.getNombre().length() > 20) {
                    errores.put("NOMBRE", "EL NOMBRE EXCEDE LA LONGITUD MAXIMA DE 20 CARACTERES");
                } else {
                    errores.put("NOMBRE", "EL NOMBRE NO PUEDE ESTAR VACIO");
                }
            }

            if (proveedor.getApellidos().length() > 30 || proveedor.getApellidos().equals("")) {
                if (proveedor.getApellidos().length() > 30) {
                    errores.put("APELLIDOS", "LOS APELLIDOS EXCEDEN LA LONGITUD MAXIMA DE 30 CARACTERES");
                } else {
                    errores.put("APELLIDOS", "LOS APELLIDOS NO PUEDEN ESTAR VACIOS");
                }
            }

            if (proveedor.getDireccion().length() > 40 || proveedor.getDireccion().equals("")) {
                if (proveedor.getDireccion().length() > 40) {
                    errores.put("DIRECCION", "LA DIRECCION EXCEDE LA LONGITUD MAXIMA DE 40 CARACTERES");
                } else {
                    errores.put("DIRECCION", "LA DIRECCION NO PUEDE ESTAR VACIA");
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

    public static ProveedoresEntity selectProveedorByCode(String codigo) {

        List listaCodigos = listaCodigosProvedores();
        List listaProveedores = listaProveedoresAll();

        // si la lista de codigos contiene el codigo que le paso...
        if (ChecksUtils.existeCodigo(listaCodigos, codigo)) {
            //recorro los porveedores y me quedo con el que tiene el codigo que le he pasado
            for (int i = 0; i < listaProveedores.size(); i++) {
                if (((ProveedoresEntity) listaProveedores.get(i)).getCodigo().equals(codigo)) {
                    return ((ProveedoresEntity) listaProveedores.get(i));
                }
            }
        }
        return null;
    }


    public static List<ProveedoresEntity> listaProveedoresAll_OLD() {

        List<ProveedoresEntity> listaProvedores = new ArrayList();

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
            System.out.println("proveedor: " + prov.toString());
            listaProvedores.add(prov);
        }
        session.close();


        return listaProvedores;
    }



}




