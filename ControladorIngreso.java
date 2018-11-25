/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Wall-E
 */
public class ControladorIngreso implements ActionListener{
    public Ingreso ventanaIngreso;
    public IngresoDAO dao;
    
    public ControladorIngreso(Ingreso ventana){
        ventanaIngreso = ventana;
        dao = new IngresoDAOImpl();
        
        this.ventanaIngreso.Ingresar.addActionListener(this);
        this.ventanaIngreso.Cancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ingresar":
                ingresar();
                break;
            case "Cancelar":
                cerrar();
                break;
            default:
                break;
        }
    }
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
            else{
                JOptionPane.showMessageDialog(ventanaIngreso, "Bienvenido "+nombreUsuario+".");
                ventanaIngreso.mostrarMenu(ventanaIngreso);
                MenuPrincipal menu = new MenuPrincipal(valida, nombreUsuario);
                menu.setVisible(true);
            }
        }
        else{
            JOptionPane.showMessageDialog(ventanaIngreso, "Todos los espacios deben llenarse");
            ventanaIngreso.clearSpaces();
        }
    }
    public void cerrar(){
        ventanaIngreso.cerrar();
    }
}
