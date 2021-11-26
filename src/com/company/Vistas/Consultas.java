package com.company.Vistas;

import com.company.Controllers.ProveedorController;
import com.company.ProveedoresEntity;
import com.company.Utils.DataComboBox;
import com.company.Utils.DataEntryUtils;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Consultas {
    private JPanel JPGeneral;
    private JPanel JPVacio;
    private JTextField TFConsultaBusqueda;
    private JButton JButtonConsultaBuscar;
    private DataComboBox JComboConsultaResultado;
    private JPanel JPConsultasBusqueda;
    private JPanel JPConsultasCombo;
    private JPanel JPConsultasData;
    private JLabel lbConsultaBusqueda;
    private JPanel JPConsultaCodigo;
    private JPanel JPConsultaNombre;
    private JPanel JPConsultaData1;
    private JPanel JPConsultaDireccion;
    private JLabel lbConsultaFixCodigo;
    private JLabel lbConsultaFixNombre;
    private JLabel lbConsultaFixData1;
    private JLabel lbConsultaFixDireccion;
    private JLabel lbConsultaResultCodigo;
    private JLabel lbConsultaResultNombre;
    private JLabel lbConsultaResultData1;
    private JLabel lbConsultaResultDireccion;

    public Consultas() {

        JButtonConsultaBuscar.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                //Inicio sesion
                SessionFactory sesion = HibernateUtil.getSessionFactory();
                Session session = sesion.openSession();
                Transaction tx = session.beginTransaction();

                //Comun, recoger texto en textField
                String buscado = TFConsultaBusqueda.getText().toUpperCase();

                switch (lbConsultaFixData1.getText().toUpperCase()) {

                    case "APELLIDO":

                        List<ProveedoresEntity> listaProv = ProveedorController.listaProveedoresAll();
                        // Obtenemos un Iterador y recorremos la lista en los cases
                        Iterator<ProveedoresEntity> iter = listaProv.iterator();

                        List listaPor = new ArrayList();

                        switch (lbConsultaBusqueda.getText()) {

                            case "Escribe CODIGO o parte del mismo":
                                //recorro el iterator
                                while (iter.hasNext()) {
                                    //extraer el objeto de coincidencias con busqueda
                                    ProveedoresEntity prov = (ProveedoresEntity) iter.next();
                                    if (prov.getCodigo().toUpperCase().contains(buscado)) {
                                        listaPor.add(prov);
                                    }
                                }
                                //ahora tengo que pasarle la lista de coincidencias al combo

                                intComboProveedor(listaPor);
                                break;

                            case "Escribe NOMBRE o parte del mismo":
                                //recorro el iterator
                                while (iter.hasNext()) {
                                    //extraer el objeto de coincidencias con busqueda
                                    ProveedoresEntity prov = (ProveedoresEntity) iter.next();
                                    if (prov.getNombre().toUpperCase().contains(buscado)) {
                                        listaPor.add(prov);
                                    }
                                }
                                //ahora tengo que pasarle la lista de coincidencias al combo
                                intComboProveedor(listaPor);
                                break;

                            case "Escribe DIRECCION o parte de la misma":
                                //recorro el iterator
                                while (iter.hasNext()) {
                                    //extraer el objeto de coincidencias con busqueda
                                    ProveedoresEntity prov = (ProveedoresEntity) iter.next();
                                    if (prov.getDireccion().toUpperCase().contains(buscado)) {
                                        listaPor.add(prov);
                                    }
                                }
                                //ahora tengo que pasarle la lista de coincidencias al combo
                                intComboProveedor(listaPor);
                                break;




                            default:
                                System.out.println("case no implementado aun");

                        }


                        break;
                    default:
                        System.out.println("case no implementado aun");

                }

            }
        });

    }


    /**
     * Esta funcion nos permite reutilizar el frame ycambiar la parte inferior donde aparecen las pantallas degestion
     * empleado, espectaculo....
     */
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


    ///////////POR PROVEEDOR

    public void ProveedorPorCodigo() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("APELLIDO");

    }

    public void ProveedorPorNombre() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("APELLIDO");


    }

    public void ProveedorPorDireccion() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe DIRECCION o parte de la misma");
        this.lbConsultaFixData1.setText("APELLIDO");


    }

    ///////////POR PIEZA

    public void PiezaPorCodigo() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("PRECIO");
        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);

    }

    public void PiezaPorNombre() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("PRECIO");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }

    ///////////POR PROYECTO

    public void ProyectoPorCodigo() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe CODIGO o parte del mismo");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);

    }

    public void ProyectoPorNombre() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe NOMBRE o parte del mismo");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }

    public void ProyectoPorCiudad() {

        //SET LABEL
        this.lbConsultaBusqueda.setText("Escribe DIRECCION o parte de la misma");
        this.lbConsultaFixData1.setText("CIUDAD");

        //SET VISIBLE
        this.JPConsultaDireccion.setVisible(false);


    }


    private void intComboProveedor(List<ProveedoresEntity> listaBuscados) {
        if (listaBuscados.size()==0){
            JOptionPane.showMessageDialog(null, "No se encuentran resultados para la búsqueda", "Avisos", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            /* Category Combo */
            JComboConsultaResultado.init();
            //List<ProveedoresEntity> cl = DBUtils.readAll(ProveedoresEntity.class); para todos los prov
            for (ProveedoresEntity c : listaBuscados) {
                JComboConsultaResultado.addRow(new Object[]{c.getId(), c.getNombre()+ " " + c.getApellidos()});
            }
            JComboConsultaResultado.repaint();


        } catch (Exception e) {
            DataEntryUtils.handleDBError(e);
        }

        /* Item listener en el combo */
        JComboConsultaResultado.addItemListener(e -> {

            if (JComboConsultaResultado.getSelectedId() > 0) {

                int id = JComboConsultaResultado.getSelectedId();

                ProveedoresEntity prov = ProveedorController.selectProveedorById(id);

                lbConsultaResultCodigo.setText(prov.getCodigo());
                lbConsultaResultNombre.setText(prov.getNombre());
                lbConsultaResultData1.setText(prov.getApellidos());
                lbConsultaResultDireccion.setText(prov.getDireccion());
            } else {
                limpiarConsultaLabel();
            }

        });

        JPVacio.repaint();
    }

    private void limpiarConsultaLabel(){
        lbConsultaResultCodigo.setText("");
        lbConsultaResultNombre.setText("");
        lbConsultaResultData1.setText("");
        lbConsultaResultDireccion.setText("");
    }


}
