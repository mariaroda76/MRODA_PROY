package com.company.Vistas;

import com.company.Controllers.PiezaController;
import com.company.Controllers.ProveedorController;
import com.company.Controllers.ProyectoController;
import com.company.PiezasEntity;
import com.company.ProveedoresEntity;
import com.company.ProyectosEntity;
import com.company.Utils.DataComboBox;
import com.company.Utils.DataEntryUtils;

import javax.swing.*;
import java.util.List;

public class GestionGlobal {
    private JPanel JPGeneral;
    private JPanel JPVacio;
    private JButton JButtonGestionGlobalInsertar;
    private JButton JButtonGestionGlobalModificar;
    private JButton JButtonGestionGlobalEliminar;
    private JButton JButtonGestionGlobalListar;
    private DataComboBox CBGBProveedor;
    private DataComboBox CBGBPieza;
    private DataComboBox CBGBProyecto;
    private JTextField TFGBCantidad;
    private JTextField TFGBDataProveedorNombre;
    private JTextField TFGBDataProveedorApellido;
    private JTextField TFGBDataPiezaNombre;
    private JTextField TFGBDataPiezaPrecio;
    private JTextField TFGBDataProyectoNombre;
    private JTextField TFGBDataProyectoCiudad;
    private JPanel JPGestionGlobalButtons;
    private JPanel JPGestionGlobal;
    private JLabel lbGestionGlobal;
    private JPanel JPGestioGlobalData;
    private JPanel JPGBProveedor;
    private JPanel JPGBPieza;
    private JPanel JPGBProyecto;
    private JPanel JPGBCantidad;
    private JLabel lbGBProveedor;
    private JPanel JPGBDataProveedor;
    private JPanel JPGBDataProveedorNombre;
    private JPanel JPGBDataProveedorApellido;
    private JLabel lbGBDataProveedorNombre;
    private JLabel lbGBDataProveedorApellido;
    private JLabel lbGBPieza;
    private JLabel lbGBProyecto;
    private JLabel lbGBCantidad;
    private JPanel JPGBDataPieza;
    private JPanel JPGBDataProyecto;
    private JPanel JPGBDataPiezaNombre;
    private JPanel JPGBDataPiezaPrecio;
    private JPanel JPGBDataProyectoNombre;
    private JPanel JPGBDataProyectoCiudad;
    private JLabel lbGBDataPiezaNombre;
    private JLabel lbGBDataPiezaPrecio;
    private JLabel lbGBDataProyectoNombre;
    private JLabel lbGBDataProyectoCiudad;


    public GestionGlobal() {


    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public JPanel getJPVacio() {

        return JPVacio;

    }

    ///////////COMBOS
    public void intComboProveedor(List<ProveedoresEntity> listaBuscados) {
        if (listaBuscados.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran resultados para el desplegable Proveedores", "Avisos", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            /* Category Combo */
            CBGBProveedor.init();
            //List<ProveedoresEntity> cl = DBUtils.readAll(ProveedoresEntity.class); para todos los prov
            for (ProveedoresEntity c : listaBuscados) {
                CBGBProveedor.addRow(new Object[]{c.getId(), c.getNombre() + " " + c.getApellidos()});
            }
            CBGBProveedor.repaint();


        } catch (Exception e) {
            DataEntryUtils.handleDBError(e);
        }

        /* Item listener en el combo */
        CBGBProveedor.addItemListener(e -> {

            if (CBGBProveedor.getSelectedId() > 0) {

                int id = CBGBProveedor.getSelectedId();

                ProveedoresEntity prov = ProveedorController.selectProveedorById(id);

                TFGBDataProveedorNombre.setText(prov.getNombre());
                TFGBDataProveedorApellido.setText(prov.getApellidos());

            } else {
                limpiarConsultaLabel();
            }

        });

        JPVacio.repaint();
    }
    public void intComboProyecto(List<ProyectosEntity> listaBuscados) {
        if (listaBuscados.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran resultados para el desplegable de Proyectos", "Avisos", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            /* Category Combo */
            CBGBProyecto.init();

            for (ProyectosEntity c : listaBuscados) {
                CBGBProyecto.addRow(new Object[]{c.getId(), c.getCodigo() + " " + c.getNombre()});
            }
            CBGBProyecto.repaint();


        } catch (Exception e) {
            DataEntryUtils.handleDBError(e);
        }

        /* Item listener en el combo */
        CBGBProyecto.addItemListener(e -> {

            if (CBGBProyecto.getSelectedId() > 0) {

                int id = CBGBProyecto.getSelectedId();

                ProyectosEntity proy = ProyectoController.selectproyectoById(id);

                TFGBDataProyectoNombre.setText(proy.getNombre());
                TFGBDataProyectoCiudad.setText(proy.getCiudad());

            } else {
                limpiarConsultaLabel();
            }

        });

        JPVacio.repaint();
    }
    public  void intComboPieza(List<PiezasEntity> listaBuscados) {
        if (listaBuscados.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran resultados para el desplegable de Piezas", "Avisos", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            /* Category Combo */
            CBGBPieza.init();

            for (PiezasEntity c : listaBuscados) {
                CBGBPieza.addRow(new Object[]{c.getId(), c.getCodigo() + " " + c.getNombre()});
            }
            CBGBPieza.repaint();


        } catch (Exception e) {
            DataEntryUtils.handleDBError(e);
        }

        /* Item listener en el combo */
        CBGBPieza.addItemListener(e -> {

            if ( CBGBPieza.getSelectedId() > 0) {

                int id =  CBGBPieza.getSelectedId();

                PiezasEntity pieza = PiezaController.selectPiezaById(id);

                TFGBDataPiezaNombre.setText(pieza.getNombre());
                TFGBDataPiezaPrecio.setText(String.valueOf(pieza.getPrecio()));


            } else {
                limpiarConsultaLabel();
            }

        });

        JPVacio.repaint();
    }

    private void limpiarConsultaLabel() {
        TFGBDataProyectoNombre.setText("");
        TFGBDataProyectoCiudad.setText("");

        TFGBDataProyectoNombre.setText("");
        TFGBDataProyectoCiudad.setText("");

        TFGBDataPiezaNombre.setText("");
        TFGBDataPiezaPrecio.setText("");

    }


}
