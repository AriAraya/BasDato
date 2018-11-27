/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wall-E
 */
public class ControladorIngreso implements ActionListener{
    public Ingreso ventanaIngreso;
    public IngresoDAO dao;
    /**
     * Constructor del controlador para ingresar al programa (logIn)
     * @param ventana ventana donde se va a trabajar
     */
    public ControladorIngreso(Ingreso ventana){
        ventanaIngreso = ventana;
        dao = new IngresoDAOImpl();
        
        this.ventanaIngreso.Ingresar.addActionListener(this);
        this.ventanaIngreso.Cancelar.addActionListener(this);
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ingresar":
                ingresar();
                break;
            case "Salir":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Método que ingresa los datos de la vista en constantes y esas constantes son pasadas al modelo
     */
    public void ingresar(){
        if(!ventanaIngreso.txtSpacesEmpty()){
            String nombreUsuario = ventanaIngreso.textoUsuario.getText();
            String contraseña = ventanaIngreso.textoContraseña.getText();
            String valida = "";
            valida = dao.validaIngreso(nombreUsuario, contraseña);
            if(valida.equals("")){
                JOptionPane.showMessageDialog(ventanaIngreso, "Nombre de usuario o contraseña incorrecto");
                ventanaIngreso.clearSpaces();
            }
            else if(valida.equals("fallo")){
                JOptionPane.showMessageDialog(ventanaIngreso, "No se pudo conectar con el servidor");
            }
            else{
                if(contraseña.equals("123Admin$")&&nombreUsuario.equals("AdminRiko")){
                    valida = dao.getBasesDeDatos();
                    JOptionPane.showMessageDialog(ventanaIngreso, "Bienvenido de vuelta "+nombreUsuario+". Estamos felices con su ingreso");
                    ventanaIngreso.mostrarMenu(ventanaIngreso);
                    System.out.println(valida);
                    MenuPrincipal menu = new MenuPrincipal(valida, nombreUsuario, contraseña);
                    ControladorMenu controladorMenu = new ControladorMenu(menu);
                    controladorMenu.mostrarVentana(menu);
                }
                else{
                    JOptionPane.showMessageDialog(ventanaIngreso, "Bienvenido "+nombreUsuario+".");
                    ventanaIngreso.mostrarMenu(ventanaIngreso);
                    System.out.println(valida);
                    MenuPrincipal menu = new MenuPrincipal(valida, nombreUsuario, contraseña);
                    ControladorMenu controladorMenu = new ControladorMenu(menu);
                    controladorMenu.mostrarVentana(menu);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(ventanaIngreso, "Todos los espacios deben llenarse");
            ventanaIngreso.clearSpaces();
        }
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaIngreso.cerrar();
    }
    /**
     * muestra la ventana de la vista
     * @param ventana 
     */
    public void mostrarVentana(Ingreso ventana){
        ventana.setVisible(true);
    }
}
