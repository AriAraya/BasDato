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
 * @author U1
 */
public class ControladorCambNombTabla implements ActionListener{
    private CambNombTabla ventanaCambiaNombre;
    private CambNombTablaDAO dao;
    private String tabla;
    private String nuevoNombre;
    private String base;
    
    public void ControladorElimUser(CambNombTabla pVentana){
        ventanaCambiaNombre = pVentana;
        dao = new CambNombTablaDAOImpl();
        
        this.ventanaCambiaNombre.ComboTabla.addActionListener(this);
        this.ventanaCambiaNombre.btnAceptar.addActionListener(this);
        this.ventanaCambiaNombre.btnVolver.addActionListener(this);
        this.ventanaCambiaNombre.txtFieldNuevNombre.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                crearTabla();
                break;
            case "Volver":
                cerrar();
                break;
            default:
                break;
        }
    }
    public void crearTabla(){
        nuevoNombre = ventanaCambiaNombre.txtFieldNuevNombre.getText();
        tabla=ventanaCambiaNombre.ComboTabla.getSelectedItem().toString();
        
        String msg = "";
        msg = dao.cambiarNombre(tabla, nuevoNombre, base);
        if(msg.equals("false")){
            JOptionPane.showMessageDialog(ventanaCambiaNombre, "No se puede cambiar el nombre.");
        }else{
            JOptionPane.showMessageDialog(ventanaCambiaNombre, "El nombre de la tabla se cambio con exito.");
        }
    }
    public void cerrar(){
        ventanaCambiaNombre.setVisible(false);
    }
    public void mostrarVentana(){
        ventanaCambiaNombre.setVisible(true);
    }
}
