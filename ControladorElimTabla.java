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
public class ControladorElimTabla implements ActionListener{
    private ElimTabla ventanaElimTabla;
    private ElimTablaDAO dao;
    private String base;
    private String nombreTabla;
    private String[] listaTablas;
    
    public ControladorElimTabla(ElimTabla pVentana){
        ventanaElimTabla = pVentana;
        dao = new ElimTablaDAOImpl();
        
        base = ventanaElimTabla.base;
        
        listaTablas = dao.cargaTablas(base).split("\n");
        
        ventanaElimTabla.cargaBox(listaTablas);
        
        this.ventanaElimTabla.btEliminar.addActionListener(this);
        this.ventanaElimTabla.btVolver.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Eliminar":
                eliminarTabla();
                break;
            case "Volver":
                cerrar();
                break;
            default:
                break;
        }
    }
    public void eliminarTabla(){
        String tabla = ventanaElimTabla.seleccionarBase();
        String msg = "";
        msg = dao.eliminaTabla(tabla, base);
        if(msg == "false"){
            JOptionPane.showMessageDialog(ventanaElimTabla, "La tabla no puede ser borrada. Posiblemente tiene datos");
        }
        else{
            JOptionPane.showMessageDialog(ventanaElimTabla, "Tabla eliminada con éxito");
            cerrar();
        }
    }
    public void cerrar(){
        this.ventanaElimTabla.cerrarVentana(ventanaElimTabla);
    }
    public void mostrarVentana(){
        this.ventanaElimTabla.mostrarVentana(ventanaElimTabla);
    }
    
}
