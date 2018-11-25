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
    
    public ControladorMenu(MenuPrincipal ventana){
        ventanaMenu = ventana;
        
        ventanaMenu.btAñadirRegistros.addActionListener(this);
        ventanaMenu.btAñadirRegistrosM.addActionListener(this);
        ventanaMenu.btAñadirUsuario.addActionListener(this);
        ventanaMenu.btCambiaNombreTabla.addActionListener(this);
        ventanaMenu.btCargaRegistros.addActionListener(this);
        ventanaMenu.btCreaBase.addActionListener(this);
        ventanaMenu.btCrearTabla.addActionListener(this);
        ventanaMenu.btEliminaRegistros.addActionListener(this);
        ventanaMenu.btEliminaTabla.addActionListener(this);
        ventanaMenu.btEliminaUsuario.addActionListener(this);
        ventanaMenu.btHtml.addActionListener(this);
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
                
                break;
            case "Crear tabla":
                
                break;
            case "Eliminar tabla":
                
                break;
            case "Cambiar nombre de tabla":
                
                break;
            case "Añadir registros manualmente":
                
                break;
            case "Añadir usuario":
                añadirUsuario();
                break;
            case "Añador registros":
                
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
