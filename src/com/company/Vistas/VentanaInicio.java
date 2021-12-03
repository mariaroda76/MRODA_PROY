package com.company.Vistas;


import com.company.Controllers.PiezaController;
import com.company.Controllers.ProveedorController;
import com.company.Controllers.ProyectoController;
import com.company.PiezasEntity;
import com.company.ProveedoresEntity;
import com.company.ProyectosEntity;
import com.company.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class VentanaInicio {


    private JLabel lbPieDeVentana;
    private JPanel JPGeneral;
    private JPanel JPVacio;
    private JPanel JPImagen;
    static private int db;


    public VentanaInicio(JFrame frame) throws IOException {


        JMenuBar menuBar = new JMenuBar();

        //Menus: Provveedores, Piezas, Piezas, Proyectos, Gestion Global, Ayuda, Base de Datos??...
        //JMenu MenuBdDatos = new JMenu("Base de Datos"); // esto pa que vale??
        JMenu MenuProveedores = new JMenu("Proveedores");
        JMenu MenuPiezas = new JMenu("Piezas");
        JMenu MenuProyectos = new JMenu("Proyectos");
        JMenu MenuGestionGlobal = new JMenu("Gestion Global");
        JMenu MenuAyuda = new JMenu("Ayuda");


        // Items a agregar a cada menu.
        JMenuItem itemGestionProveedores = new JMenuItem("Gestión de Proveedores");
        JMenu MenuConsultasProveedores = new JMenu("Consulta de Proveedores");

        JMenuItem itemGestionPiezas = new JMenuItem("Gestión de Piezas");
        JMenu MenuConsultasPiezas = new JMenu("Consulta de Piezas");

        JMenuItem itemGestionProyectos = new JMenuItem("Gestión de Proyectos");
        JMenu MenuConsultasProyectos = new JMenu("Consulta de Proyectos");

        JMenuItem itemPiezasProveedoresProyectos = new JMenuItem("Piezas, Proveedores y Proyectos");
        JMenuItem itemSuministrosProveedor = new JMenuItem("Suministros por Proveedor");
        JMenuItem itemSuministrosPiezas = new JMenuItem("Suministros por Piezas");
        JMenuItem itemEstadisticas = new JMenuItem("Estadisticas");

        JMenuItem itemAyuda = new JMenuItem("Ayuda");


        // Items comunes
        JMenuItem itemPiezaPorCodigo = new JMenuItem("Por Código");
        JMenuItem itemPiezaPorNombre = new JMenuItem("Por Nombre");


        JMenuItem itemProveedorPorCodigo = new JMenuItem("Por Código");
        JMenuItem itemProveedorPorNombre = new JMenuItem("Por Nombre");
        JMenuItem itemProveedorPorDireccion = new JMenuItem("Por Dirección");

        JMenuItem itemProyectoPorCodigo = new JMenuItem("Por Código");
        JMenuItem itemProyectoPorNombre = new JMenuItem("Por Nombre");
        JMenuItem itemProyectoPorCiudad = new JMenuItem("Por Ciudad");


        // Aqui añadimos el item a cada menu.

        //MenuBdDatos.add();

        MenuProveedores.add(itemGestionProveedores);
        MenuProveedores.add(MenuConsultasProveedores);

        MenuPiezas.add(itemGestionPiezas);
        MenuPiezas.add(MenuConsultasPiezas);

        MenuProyectos.add(itemGestionProyectos);
        MenuProyectos.add(MenuConsultasProyectos);

        MenuGestionGlobal.add(itemPiezasProveedoresProyectos);
        MenuGestionGlobal.add(itemSuministrosPiezas);
        MenuGestionGlobal.add(itemSuministrosProveedor);
        MenuGestionGlobal.add(itemEstadisticas);


        MenuAyuda.add(itemAyuda);


        // Añadir items a submenu

        MenuConsultasProyectos.add(itemProyectoPorCodigo);
        MenuConsultasProyectos.add(itemProyectoPorNombre);
        MenuConsultasProyectos.add(itemProyectoPorCiudad);

        MenuConsultasProveedores.add(itemProveedorPorCodigo);
        MenuConsultasProveedores.add(itemProveedorPorNombre);
        MenuConsultasProveedores.add(itemProveedorPorDireccion);

        MenuConsultasPiezas.add(itemPiezaPorCodigo);
        MenuConsultasPiezas.add(itemPiezaPorNombre);


        //Formamos el menu bar
        //menuBar.add(MenuBdDatos);
        menuBar.add(MenuProveedores);
        menuBar.add(MenuPiezas);
        menuBar.add(MenuProyectos);
        menuBar.add(MenuGestionGlobal);
        menuBar.add(MenuAyuda);


/*        SimpleBackground.BackgroundPane background = new SimpleBackground.BackgroundPane();
        background.setBackground(ImageIO.read(new File("C:\\Users\\Fran\\Desktop\\maria\\curso3\\AD\\PROYECTO-2-AD_ADRIAN\\MRODA_PROY_GIT\\MRODA_PROY\\doc_proy\\logo.png")));
        frame.setContentPane(background);
        frame.setLayout(new GridBagLayout());*/


    /*   String path = "C:\\Users\\Fran\\Desktop\\maria\\curso3\\AD\\PROYECTO-2-AD_ADRIAN\\MRODA_PROY_GIT\\MRODA_PROY\\doc_proy\\logo.png";
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        JLabel label = new JLabel(new ImageIcon(image));

        frame.add(label);
        frame.setLayout(new GridLayout(50, 50));
        frame.repaint();*/


        frame.add(menuBar); //Añadir el menu bar al frame. Se tiene que añadir al frame principal porque de este se arrastra a todos.
        frame.setJMenuBar(menuBar);


        //Añadir evento del cierre de ventana para controlar el cierre de la conexion de Base de datos.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //ArrancarPrograma.db.DesconectarDb();
                System.out.println("Cerrando bases de datos");
            }
        });


        /**Cada vez que pulsemos en un item nos abrirá el panel inferior nuevo con los campos correspondientes a la tabla*/
        itemGestionProveedores.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {


                JFrame frameGestionProveedor = new JFrame("Gestion de Proveedores");
                Gestion nuevaGestionProveedor = new Gestion();
                nuevaGestionProveedor.gestionProveedor();

                frameGestionProveedor.setContentPane(nuevaGestionProveedor.getJPGeneral());

                //frameGestionProveedor .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameGestionProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameGestionProveedor.pack();
                frameGestionProveedor.setVisible(true);

            }
        });
        itemGestionPiezas.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {

                JFrame frameGestionPieza = new JFrame("Gestion de Piezas");
                Gestion nuevaGestionPieza = new Gestion();
                nuevaGestionPieza.gestionPieza();

                frameGestionPieza.setContentPane(nuevaGestionPieza.getJPGeneral());

                frameGestionPieza.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameGestionPieza.pack();
                frameGestionPieza.setVisible(true);

            }
        });
        itemGestionProyectos.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {

                JFrame frameGestionProyecto = new JFrame("Gestion de Proyectos");
                Gestion nuevaGestionProyecto = new Gestion();
                nuevaGestionProyecto.gestionProyectos();

                frameGestionProyecto.setContentPane(nuevaGestionProyecto.getJPGeneral());

                frameGestionProyecto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameGestionProyecto.pack();
                frameGestionProyecto.setVisible(true);

            }
        });


        itemProveedorPorCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proveedor", "Codigo");

            }
        });
        itemProveedorPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proveedor", "Nombre");

            }
        });
        itemProveedorPorDireccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proveedor", "Direccion");

            }
        });


        itemPiezaPorCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Pieza", "Codigo");

            }
        });
        itemPiezaPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Pieza", "Nombre");

            }
        });

        itemProyectoPorCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proyecto", "Codigo");

            }
        });
        itemProyectoPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proyecto", "Nombre");

            }
        });
        itemProyectoPorCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Proyecto", "Ciudad");

            }
        });

        itemPiezasProveedoresProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frameGestionGlobalPPP = new JFrame("Gestion Global: Proveedores - Piezas - Proyectos");
                GestionGlobal nuevaGestionGlobalPPP = new GestionGlobal();

                frameGestionGlobalPPP.setContentPane(nuevaGestionGlobalPPP.getJPGeneral());

                frameGestionGlobalPPP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameGestionGlobalPPP.pack();
                frameGestionGlobalPPP.setVisible(true);

                //para estos combos listo solo activos
                List<ProveedoresEntity> listaProv = ProveedorController.listaProveedoresState(false);
                nuevaGestionGlobalPPP.intComboProveedor(listaProv);
                List<ProyectosEntity> listaProy = ProyectoController.listaProyectosState(false);
                nuevaGestionGlobalPPP.intComboProyecto(listaProy);
                List<PiezasEntity> listaPieza = PiezaController.listaPiezasState(false);
                nuevaGestionGlobalPPP.intComboPieza(listaPieza);

            }
        });

        itemSuministrosPiezas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Proximamente Disponible en V.02", "Suministros por Pieza", JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        itemSuministrosProveedor.addActionListener(new ActionListener() {

            //Inicio sesion
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();


            @Override
            public void actionPerformed(ActionEvent e) {

                consultas("Suministro_Proveedor", "Codigo");

/*                JOptionPane.showMessageDialog(null, "Proximamente Disponible en V.02", "Suministros por Proveedor", JOptionPane.INFORMATION_MESSAGE
                );*/

            }
        });
        itemEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Proximamente Disponible en V.02", "Estadisticas", JOptionPane.INFORMATION_MESSAGE
                );

            }
        });

        itemAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAyuda = new JFrame("Ayuda al Usuario");
                Ayuda ayuda = new Ayuda();

                frameAyuda.setContentPane(ayuda.getJPGeneral());

                frameAyuda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frameAyuda.pack();
                frameAyuda.setVisible(true);


            }
        });


    }

    public static void main(String[] args) throws IOException {

        /**
         * Arrancamos la aplicacion desde este punto.
         * */

        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(Level.OFF);

        JFrame frame = new JFrame("Gestion de Proyectos");

        /*Añadimos un listener al frame principal para que cierre la conexion de
         * la base de datos que esté siendo usada.
         */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.out.println("Base de datos desconectada");

            }
        });

        //desde el frame estoy arrancado el menu


        frame.setContentPane(new VentanaInicio(frame).JPGeneral);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


    /**
     * Esta funcion nos permite reutilizar el frame y solo cambiar la parte inferior donde aparecen las pantallas de cliente
     * empleado, espectaculo....
     */
    public void mostrarPanel(JPanel panel) {

        JPVacio.removeAll();
        JPVacio.add(panel);
        JPVacio.repaint();
        JPVacio.revalidate();


    }

    public void setLbTituloParque(String lbTituloParque) {
        this.lbPieDeVentana.setText(lbTituloParque);
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public JPanel getJPVacio() {

        return JPVacio;

    }

    void showRequest(JFrame frame) {

        frame.setVisible(false);
        frame = frame;
        frame.setVisible(true);
    }

    public void consultas(String que, String como) {
        JFrame frameConsulta = new JFrame("Consulta de " + que + " Por " + como);
        Consultas nuevaConsulta = new Consultas();

        switch (que) {
            case "Proveedor":
                switch (como) {

                    case "Codigo":
                        nuevaConsulta.ProveedorPorCodigo();
                        break;
                    case "Nombre":
                        nuevaConsulta.ProveedorPorNombre();
                        break;
                    default:
                        nuevaConsulta.ProveedorPorDireccion();
                }
                break;
            case "Pieza":
                switch (como) {
                    case "Codigo":
                        nuevaConsulta.PiezaPorCodigo();
                        break;
                    default:
                        nuevaConsulta.PiezaPorNombre();
                }
                break;
            case "Suministro_Proveedor":
                switch (como) {
                    case "Codigo":
                        nuevaConsulta.Suministro_ProveedorPorCodigo();
                        break;
                    default:
                        nuevaConsulta.Suministro_ProveedorPorCodigo();
                }break;

            default:
                switch (como) {

                    case "Codigo":
                        nuevaConsulta.ProyectoPorCodigo();
                        break;
                    case "Nombre":
                        nuevaConsulta.ProyectoPorNombre();
                        break;
                    default:
                        nuevaConsulta.ProyectoPorCiudad();
                }

                break;

        }


        frameConsulta.setContentPane(nuevaConsulta.getJPGeneral());

        frameConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frameConsulta.pack();
        frameConsulta.setVisible(true);

    }


}