package com.company.Utils;

import java.util.List;

public class ChecksUtils {

    public static Boolean existeCodigo(List lista, String codigo) {

        if (lista.contains(codigo.toUpperCase())) {
            return true;
        }
        return false;
    }

}
