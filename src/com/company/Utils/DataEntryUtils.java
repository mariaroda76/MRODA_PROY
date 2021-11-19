package com.company.Utils;


import javax.swing.*;

class DataEntryUtils {
    public static boolean confirmDBSave() {
        return confirm("Seguro que quieres salvar?");
    }

    public static boolean confirmDBUpdate() {
        return confirm("Seguro que quieres modificar?");
    }

    public static boolean confirmDBDelete() {
        return confirm("Seguro que quieres eliminar?");
    }

    private static boolean confirm(String msg) {
        int answer = JOptionPane.showConfirmDialog(null, msg, "Estas seguro ?", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            return true;
        }
        return false;
    }
}
