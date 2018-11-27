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
    
    /**
     * Constructor del controlador para añadir usuarios
     * @param pVentana ventana con la cual va a trabajar el controlador
     */
    public ControladorAnadirUser(AnadirUser pVentana){
        ventanaAnadir = pVentana;
        dao = new AnadirDAOImpl();
        
        this.ventanaAnadir.btAceptar.addActionListener(this);
        this.ventanaAnadir.btCancelar.addActionListener(this);
        this.ventanaAnadir.btAgregarBase.addActionListener(this);
        this.ventanaAnadir.boxBases.addActionListener(this);
        
        listaBases = dao.getBasesDeDatos().split("\n");
        System.out.println(listaBases);
        ventanaAnadir.cargaBases(listaBases);
        ventanaAnadir.boxBases.setVisible(true);
        ventanaAnadir.boxBases.setSelectedIndex (0);
    }
    /**
     * Obtiene la acción que hace la vista para añadir usuarios
     * @param e evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                anadirUser();
                break;
            case "+":
                anadirBase();
                break;
            case "Cancelar":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Método para crear el usuario, donde obtiene los datos desde la vista y manda esos datos
     * al modelo para crear el usuario
     */
    public void anadirUser(){
        if(ventanaAnadir.validaEspacios()){
            String nombre = ventanaAnadir.txtNombre.getText();
            String contraseña = ventanaAnadir.txtContraseña.getText();
            String bases;
            bases = ventanaAnadir.txtBasesGuardadas.getText();
            bases = bases.substring(1, bases.length());
            System.out.println(bases);
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
    /**
     * cierra la ventana
     */
    public void cerrar(){
        ventanaAnadir.setVisible(false);
    }
    /**
     * carga el texto de la vista
     */
    public void anadirBase(){
        ventanaAnadir.agregarTexto();
    }
    /**
     * muestra la vista
     */
    public void mostrarVentana(){
        ventanaAnadir.setVisible(true);
    }
}
