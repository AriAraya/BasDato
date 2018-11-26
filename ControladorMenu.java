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
                
                break;
            case "Seleccionar datos":
                
                break;
            case "Reporte en HTML":
                
                break;
            case "Cargar registros":
                
                break;
            case "Salir":
                cerrar();
                break;
            default:
                break;
        }
    }
    public void añadirUsuario(){
        AnadirUser ventanaAñadir = new AnadirUser();
        ControladorAnadirUser controladorAnadirUser = new ControladorAnadirUser(ventanaAñadir);
        controladorAnadirUser.mostrarVentana();
    }
    public void eliminaUsuario(){
        ElimUser ventanaEliminaUser = new ElimUser();
        ControladorElimUser controlador = new ControladorElimUser(ventanaEliminaUser);
        controlador.mostrarVentana();
    }
    public void crearBase(){
        CreaBase ventanaCreaBase = new CreaBase();
        ControladorCreaBase controlador = new ControladorCreaBase(ventanaCreaBase);
        controlador.mostrarVentana();
    }
    public void crearTabla(){
        Tabla ventanaTabla = new Tabla(bases, "crear");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    public void eliminarTabla(){
        Tabla ventanaTabla = new Tabla(bases, "eliminar");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    public void cambiarNombreTabla(){
        Tabla ventanaTabla = new Tabla(bases, "cambiar");
        ControladorTabla controlador = new ControladorTabla(ventanaTabla);
        controlador.mostrarVentana();
    }
    public void cerrar(){
        ventanaMenu.cerrarVentana(ventanaMenu);
        Ingreso ventanaIngreso = new Ingreso();
        ControladorIngreso controladorIngreso = new ControladorIngreso(ventanaIngreso);
        controladorIngreso.mostrarVentana(ventanaIngreso);
    }
    public void mostrarVentana(MenuPrincipal ventana){
        ventana.setVisible(true);
    }
}