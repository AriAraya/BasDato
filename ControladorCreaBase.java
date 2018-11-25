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
    
    public ControladorCreaBase(CreaBase ventana){
        ventanaCreaBase = ventana;
        dao = new CrearBaseDAOImpl();
        
        this.ventanaCreaBase.btAceptar.addActionListener(this);
        this.ventanaCreaBase.btSalir.addActionListener(this);
    }
    
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
    public void cerrar(){
        ventanaCreaBase.setVisible(false);
    }
    public void mostrarVentana(){
        ventanaCreaBase.mostrarVentana(ventanaCreaBase);
    }
    
}
