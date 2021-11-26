package com.company.Utils;

import java.util.List;

public class DBUtils {

    private static synchronized BaseDBUtils getUtils() throws Exception {
        return new BaseDBUtils();
    }

    public static List readAll(Class clazz) throws Exception {
        return getUtils().readAll(clazz);
    }

    public static void saveOrUpdate(Object object) throws Exception {
        getUtils().saveOrUpdate(object);
    }


    public static Object getById(Class clazz, int id) throws Exception {
        return getUtils().getById(clazz, id);
    }


    public static int deleteById(Class clazz, int id) throws Exception {
        return getUtils().deleteById(clazz, id);
    }


}
