package com.company.Utils;

import org.hibernate.Query;
import java.util.List;

/**
 * Funciones de BD usadas frecuentemente
 *
 */
public class BaseDBUtils extends BaseDAO {

    String id_ = "id";
    

    public BaseDBUtils() {
        super();
    }

    public final List readAll(Class clazz) throws Exception {
        Query q = getSession().createQuery("from " + clazz.getName() + "  order by " + id_ + " desc");
        return super.runReadQuery(q);
    }


    public final Object getById(Class clazz, int id) throws Exception {
        Query q = getSession().createQuery("from " + clazz.getName() + " where " + id_ + "=:id order by " + id_ + " desc");
        q.setInteger("id", id);
        return super.runReadQuery(q).get(0);

    }


    public final int deleteById(Class clazz, int id) throws Exception {
        Query q = getSession().createQuery("delete from " + clazz.getName() + " where " + id_ + "=:id ");
        q.setInteger("id", id);
        return super.runQuery(q);

    }

    public final void saveOrUpdate(Object object) throws Exception {
        super.saveOrUpdate(object);

    }
}


