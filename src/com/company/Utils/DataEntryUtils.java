package com.company.Utils;

import javax.swing.*;

public class DataEntryUtils {
    public static boolean confirmDBSave(String datos) {
        return confirm("estas a punto de GUARDAR " + datos);
    }

    public static boolean confirmDBUpdate(String datos) {
        return confirm("estas a punto de MODIFICAR " + datos);
    }

    public static boolean confirmDBBaja(String datos) {
        return confirm("estas a punto de DAR DE BAJA " + datos);
    }

    public static boolean confirmDBDelete(String datos) {
        return confirm("estas a punto de ELIMINAR: " + datos);
    }

    private static boolean confirm(String msg) {
        int answer = JOptionPane.showConfirmDialog(null, msg, "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            return true;
        }
        return false;
    }



}
