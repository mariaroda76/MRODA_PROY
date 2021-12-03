package com.company.Vistas;

import com.company.Controllers.GestionController;
import com.company.Controllers.PiezaController;
import com.company.Controllers.ProveedorController;
import com.company.Controllers.ProyectoController;
import com.company.GestionEntity;
import com.company.PiezasEntity;
import com.company.ProveedoresEntity;
import com.company.ProyectosEntity;
import com.company.Utils.DataComboBox;
import com.company.Utils.DataEntryUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel lista;


    public GestionGlobal() {


        JButtonGestionGlobalInsertar.addActionListener(new ActionListener() {

            //Inicio sesion
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();


            @Override
            public void actionPerformed(ActionEvent e) {

                String valor = (TFGBCantidad.getText().trim());
                if (esDouble(valor)) {

                    GestionEntity pedido = getGestionFromForm();

                    if (GestionController.validaciones(pedido, 0) == null) {
                        //pido confirmacion antes de guardar
                        if (DataEntryUtils.confirmDBSave(pedido.toString())) {
                            session.save(pedido);
                            JOptionPane.showMessageDialog(null, "Se ha INSERTADO correctamente un nuevo Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                            );
                            limpiarJTextFields(JPGestioGlobalData);
                            limpiarJCombos(JPGestioGlobalData);
                            try {
                                tx.commit();
                            } catch (Exception e1) {
                                System.out.println("ERROR NO CONTROLADO");
                                System.out.printf("MENSAJE:%s%n", e1.getMessage());
                            }
                            session.close();
                        } else {
                            JOptionPane.showMessageDialog(null, "Has declinado insertar un nuevo Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } else {
                        //En este string guardamos todos los errores, y lo mostramos.
                        String texto = GestionController.validaciones(pedido, 0);
                        JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad no puede quedar vacía y debe ser un valor numérico", "Error en tipo de dato", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        JButtonGestionGlobalModificar.addActionListener(new ActionListener() {
            //CUIDADO VERIFICAR SI EXISTE LA COMBINACION!!!
            //Inicio sesion
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();

            @Override
            public void actionPerformed(ActionEvent e) {

                String valor = (TFGBCantidad.getText().trim());
                if (esDouble(valor)) {
                    GestionEntity pedido = getGestionFromForm();
                    if (GestionController.validaciones(pedido, 1) == null) {

                        //pido confirmacion antes de MODIFICAR
                        if (DataEntryUtils.confirmDBUpdate(pedido.toString())) {
                            session.update(pedido);
                            JOptionPane.showMessageDialog(null, "Se ha MODIFICADO correctamente el Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                            );
                            limpiarJTextFields(JPGestioGlobalData);
                            limpiarJCombos(JPGestioGlobalData);
                            try {
                                tx.commit();
                            } catch (Exception e1) {
                                System.out.println("ERROR NO CONTROLADO");
                                System.out.printf("MENSAJE:%s%n", e1.getMessage());
                            }
                            session.close();
                        } else {
                            JOptionPane.showMessageDialog(null, "Has declinado MODIFICAR  el Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                    } else {
                        //En este string guardamos todos los errores, y lo mostramos.
                        String texto = GestionController.validaciones(pedido, 1);
                        JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad no puede quedar vacía y debe ser un valor numérico", "Error en tipo de dato", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        JButtonGestionGlobalEliminar.addActionListener(new ActionListener() {

            //Inicio sesion
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();

            @Override
            public void actionPerformed(ActionEvent e) {

                GestionEntity pedido = getGestionFromForm();

                if (GestionController.validaciones(pedido, 2) == null) {

                    CBGBPieza.setEnabled(false);
                    CBGBProveedor.setEnabled(false);
                    CBGBProyecto.setEnabled(false);

                    //pido confirmacion antes de dar de baja
                    if (DataEntryUtils.confirmDBDelete(pedido.toStringEliminar())) {
                        session.delete(pedido);
                        JOptionPane.showMessageDialog(null, "Se ha ELIMINADO correctamente el Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                        );
                        limpiarJTextFields(JPGestioGlobalData);
                        limpiarJCombos(JPGestioGlobalData);
                        try {
                            tx.commit();
                        } catch (Exception e1) {
                            System.out.println("ERROR NO CONTROLADO");
                            System.out.printf("MENSAJE:%s%n", e1.getMessage());
                        }
                        limpiarJTextFields(JPGestioGlobalData);
                        limpiarJCombos(JPGestioGlobalData);
                        session.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Has declinado ELIMINAR al Pedido", "Mensaje: ", JOptionPane.INFORMATION_MESSAGE
                        );
                        limpiarJTextFields(JPGestioGlobalData);
                        limpiarJCombos(JPGestioGlobalData);
                    }


                } else {
                    //En este string guardamos todos los errores, y lo mostramos.
                    String texto = GestionController.validaciones(pedido, 2);
                    JOptionPane.showMessageDialog(null, texto, "Resultado", JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        });
        JButtonGestionGlobalListar.addActionListener(new ActionListener() {

            //Inicio sesion
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frameListaPedidos = new JFrame("Listado: Pedidos");
                Listado listado = new Listado(1,0);

                frameListaPedidos.setContentPane(listado.getJPGestionesTodas());
                frameListaPedidos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                listado.getJPGestionesTodas().repaint();

                frameListaPedidos.pack();
                frameListaPedidos.setVisible(true);

            }
        });
    }

    public void mostrarPanel(JPanel panel) {

        JPVacio.removeAll();
        JPVacio.add(panel);
        JPVacio.repaint();
        JPVacio.revalidate();

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
                TFGBDataProveedorNombre.setText("");
                TFGBDataProveedorApellido.setText("");
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
                TFGBDataProyectoNombre.setText("");
                TFGBDataProyectoCiudad.setText("");
            }

        });

        JPVacio.repaint();
    }

    public void intComboPieza(List<PiezasEntity> listaBuscados) {
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

            if (CBGBPieza.getSelectedId() > 0) {

                int id = CBGBPieza.getSelectedId();

                PiezasEntity pieza = PiezaController.selectPiezaById(id);

                TFGBDataPiezaNombre.setText(pieza.getNombre());
                TFGBDataPiezaPrecio.setText(String.valueOf(pieza.getPrecio()));


            } else {
                TFGBDataPiezaNombre.setText("");
                TFGBDataPiezaPrecio.setText("");
            }

        });

        JPVacio.repaint();
    }

    private void limpiarConsultaLabel() {
        TFGBDataProveedorNombre.setText("");
        TFGBDataProveedorApellido.setText("");

        TFGBDataProyectoNombre.setText("");
        TFGBDataProyectoCiudad.setText("");

        TFGBDataPiezaNombre.setText("");
        TFGBDataPiezaPrecio.setText("");

    }


    private GestionEntity getGestionFromForm() {

        GestionEntity pedido = new GestionEntity();
        pedido.setCodproveedor(CBGBProveedor.getSelectedId());
        pedido.setCodproyecto(CBGBProyecto.getSelectedId());
        pedido.setCodpieza(CBGBPieza.getSelectedId());
        if (!TFGBCantidad.getText().trim().equals("")) {
            pedido.setCantidad(Double.parseDouble(TFGBCantidad.getText()));
        } else {
            pedido.setCantidad(0.0);
        }

        return pedido;
    }

    private boolean esDouble(String precio) {

        boolean numeric = true;

        try {
            Double num = Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if (numeric) {
            return true;
        } else {
            return false;
        }

    }

    private void limpiarJTextFields(Container container) {

        Component[] mycomponents = container.getComponents();

        for (Component component : mycomponents) {
            if (component instanceof JTextField) {
                component.setEnabled(true);
                ((JTextField) component).setText("");

            } else if (component instanceof JPanel) {
                Container mypanel = (Container) component;
                limpiarJTextFields(mypanel);

            }

        }
    }

    private void limpiarJCombos(Container container) {

        CBGBProveedor.setSelectedIndex(0);
        CBGBPieza.setSelectedIndex(0);
        CBGBProyecto.setSelectedIndex(0);

        CBGBPieza.setEnabled(true);
        CBGBProveedor.setEnabled(true);
        CBGBProyecto.setEnabled(true);

    }


}
