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
public class ControladorCreaBase implements ActionListener {
   private CrearBaseDAO dao;
   private CreaBase ventanaCreaBase;
    /**
     * Contructor del controlador para crear una base de datos.
     * @param ventana vista con la cual va a trabajar el controlador
     */
    public ControladorCreaBase(CreaBase ventana){
        ventanaCreaBase = ventana;
        dao = new CrearBaseDAOImpl();
        
        this.ventanaCreaBase.btAceptar.addActionListener(this);
        this.ventanaCreaBase.btSalir.addActionListener(this);
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la accion dada
     * @param e acción dada por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                crearBase();
                break;
            case "Cancelar":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Crea la base de datos. Obtiene los datos por medio de datos de la vista y manda esos datos a el
     * modelo
     */
    public void crearBase(){
        String msg="";
        String nombre = ventanaCreaBase.txtNombre.getText();
        if(!ventanaCreaBase.espacioIsEmpty()){
            msg = dao.crearBase(nombre);
            if(msg.equals("false")){
                JOptionPane.showMessageDialog(ventanaCreaBase, "La base no se ha podido crear");
            }
            else{
                JOptionPane.showMessageDialog(ventanaCreaBase, "La base se ha creado con éxito");
                cerrar();
            }
        }
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaCreaBase.setVisible(false);
    }
    /**
     * Muestra la ventana de la vista
     */
    public void mostrarVentana(){
        ventanaCreaBase.mostrarVentana(ventanaCreaBase);
    }
    
}
