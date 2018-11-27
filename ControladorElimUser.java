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
public class ControladorElimUser implements ActionListener{
    private ElimUser ventanaElimUsar;
    private ElimUserDAO dao;
    private String[] listaUsuarios;
    /**
     * Constructor del controlador para eliminar usuario
     * @param pVentana ventana de la vista en la cual se va a trabajar
     */
    public ControladorElimUser(ElimUser pVentana){
        ventanaElimUsar = pVentana;
        dao = new ElimUserDAOImpl();
        
        this.ventanaElimUsar.btEliminar.addActionListener(this);
        this.ventanaElimUsar.btCancelar.addActionListener(this);
        this.ventanaElimUsar.boxUsers.addActionListener(this);
        
        listaUsuarios = dao.getUsers().split("\n");
        ventanaElimUsar.cargaUsuarios(listaUsuarios);
        ventanaElimUsar.boxUsers.setVisible(true);
        ventanaElimUsar.boxUsers.setSelectedIndex(0);
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Eliminar":
                removeUser();
                break;
            case "Cancelar":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Método que remueve un usuario, donde los datos se obtienen a traves de la vista
     */
    public void removeUser(){
        String nombre = ventanaElimUsar.seleccionarUsuario();
        String msg = "";
        if(!nombre.isEmpty()){
            msg = dao.removeUser(nombre);
            if(msg.equals("false")){
                JOptionPane.showMessageDialog(ventanaElimUsar, "No se puede eliminar el usuario porque posiblmente sea el administrador");
            }
            else{
                JOptionPane.showMessageDialog(ventanaElimUsar, "El usuario: "+nombre+" se ha eliminado con éxito");
                cerrar();
            }
        }
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaElimUsar.setVisible(false);
    }
    /**
     * muestra la ventana de la vista
     */
    public void mostrarVentana(){
        ventanaElimUsar.setVisible(true);
    }
}
