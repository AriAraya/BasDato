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
public class ControladorAnadirUser implements ActionListener{
    private AnadirUser ventanaAnadir;
    private AnadirUserDAO dao;
    private String[] listaBases;
    
    public ControladorAnadirUser(AnadirUser pVentana){
        ventanaAnadir = pVentana;
        dao = new AnadirDAOImpl();
        
        this.ventanaAnadir.btAceptar.addActionListener(this);
        this.ventanaAnadir.btCancelar.addActionListener(this);
        this.ventanaAnadir.btAgregarBase.addActionListener(this);
        this.ventanaAnadir.boxBases.addActionListener(this);
        
        listaBases = dao.getBasesDeDatos().split("\n");
        ventanaAnadir.cargaBases(listaBases);
        ventanaAnadir.boxBases.setVisible(true);
        ventanaAnadir.boxBases.setSelectedIndex (0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                anadirUser();
                break;
            case "+":
                anadirBase(e);
                break;
            case "Cancelar":
                cerrar();
                break;
            default:
                break;
        }
    }
    public void anadirUser(){
        if(ventanaAnadir.validaEspacios()){
            String nombre = ventanaAnadir.txtNombre.getText();
            String contraseña = ventanaAnadir.txtContraseña.getText();
            String bases;
            bases = ventanaAnadir.txtBasesGuardadas.getText();
            String mensaje = dao.anadirUser(nombre, contraseña, bases);
            if(mensaje.equals("false")){
                JOptionPane.showMessageDialog(ventanaAnadir, "El usuario ya está registrado");
            }
            else{
                JOptionPane.showMessageDialog(ventanaAnadir, "El usuario "+nombre+" fue registrado con éxito");
                cerrar();
            }
        }
    }
    public void cerrar(){
        ventanaAnadir.setVisible(false);
    }
    public void anadirBase(ActionEvent e){
        ventanaAnadir.agregarTexto();
    }
    public void mostrarVentana(){
        ventanaAnadir.setVisible(true);
    }
}
