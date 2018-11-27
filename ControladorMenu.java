/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wall-E
 */
public class ControladorMenu implements ActionListener{
    public MenuPrincipal ventanaMenu;
    public String bases;
    /**
     * Contructor del controlador para mostrar el menú
     * @param ventana 
     */
    public ControladorMenu(MenuPrincipal ventana){
        ventanaMenu = ventana;
        bases = ventanaMenu.bases;
        
        ventanaMenu.btAñadirRegistros.addActionListener(this);
        ventanaMenu.btOrdenarRegistros.addActionListener(this);
        ventanaMenu.btAñadirUsuario.addActionListener(this);
        ventanaMenu.btCambiarNombreTabla.addActionListener(this);
        ventanaMenu.btCargarRegistros.addActionListener(this);
        ventanaMenu.btCrearBase.addActionListener(this);
        ventanaMenu.btCrearTabla.addActionListener(this);
        ventanaMenu.btEliminarRegistros.addActionListener(this);
        ventanaMenu.btEliminarTabla.addActionListener(this);
        ventanaMenu.btEliminarUsuario.addActionListener(this);
        ventanaMenu.btReporteHTML.addActionListener(this);
        ventanaMenu.btSeleccionarDatos.addActionListener(this);
        ventanaMenu.btSalir.addActionListener(this);
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Eliminar usuario":
                eliminaUsuario();
                break;
            case "Crear base de datos":
                crearBase();
                break;
            case "Crear tabla":
                crearTabla();
                break;
            case "Eliminar tabla":
                eliminarTabla();
                break;
            case "Cambiar nombre de tabla":
                cambiarNombreTabla();
                break;
            case "OrdenarRegistros":
                
                break;
            case "Añadir usuario":
                añadirUsuario();
                break;
            case "Añadir registros":
                añadirRegistro();
                break;
            case "Seleccionar datos":
                
                break;
            case "Reporte en HTML":
                generarHTML();
                break;
            case "Cargar registros":
                cargaRegistros();
                break;
            case "Eliminar registros":
                eliminarRegistros();
                break;
            case "Salir":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Método que crea una vista y un controlador para añadir un usuario
     */
    public void añadirUsuario(){
        AnadirUser ventanaAñadir = new AnadirUser();
        ControladorAnadirUser controladorAnadirUser = new ControladorAnadirUser(ventanaAñadir);
        controladorAnadirUser.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para eliminar un usuario
     */
    public void eliminaUsuario(){
        ElimUser ventanaEliminaUser = new ElimUser();
        ControladorElimUser controlador = new ControladorElimUser(ventanaEliminaUser);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para crear una base
     */
    public void crearBase(){
        CreaBase ventanaCreaBase = new CreaBase();
        ControladorCreaBase controlador = new ControladorCreaBase(ventanaCreaBase);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para crear una tabla
     */
    public void crearTabla(){
        Tabla ventanaTabla = new Tabla(bases, "crear");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para eliminar una tabla
     */
    public void eliminarTabla(){
        Tabla ventanaTabla = new Tabla(bases, "eliminar");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para cambiarle el nombre a una tabla
     */
    public void cambiarNombreTabla(){
        Tabla ventanaTabla = new Tabla(bases, "cambiar");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para añadir un registro a una tabla
     */
    public void añadirRegistro(){
        Tabla ventanaTabla = new Tabla(bases, "registroM");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para generar el HTML
     */
    public void generarHTML(){
        Tabla ventanaTabla = new Tabla(bases, "HTML");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para cargar registros de archivos
     */
    public void cargaRegistros(){
        Tabla ventanaTabla = new Tabla(bases,"RegistrosFrom");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Método crea una vista y un controlador para eliminar registros
     */
    public void eliminarRegistros(){
        Tabla ventanaTabla = new Tabla(bases,"eliminarRegistros");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    /**
     * Cierra la ventana de la vista actual y crea una vista y un controlador para ingresar al programa
     * (LogOut)
     */
    public void cerrar(){
        ventanaMenu.cerrarVentana(ventanaMenu);
        Ingreso ventanaIngreso = new Ingreso();
        ControladorIngreso controladorIngreso = new ControladorIngreso(ventanaIngreso);
        controladorIngreso.mostrarVentana(ventanaIngreso);
    }
    /**
     * Muestra la ventana de una vista
     * @param ventana vista que va a ser cerrada
     */
    public void mostrarVentana(MenuPrincipal ventana){
        ventana.setVisible(true);
    }
}