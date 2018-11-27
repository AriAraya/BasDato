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
    /**
     * Constructor del controlador para eliminar una tabla
     * @param pVentana ventana de la vista con la que se va a trabajar
     */
    public ControladorElimTabla(ElimTabla pVentana){
        ventanaElimTabla = pVentana;
        dao = new ElimTablaDAOImpl();
        
        base = ventanaElimTabla.base;
        
        listaTablas = dao.cargaTablas(base).split("\n");
        
        ventanaElimTabla.cargaBox(listaTablas);
        
        this.ventanaElimTabla.btEliminar.addActionListener(this);
        this.ventanaElimTabla.btVolver.addActionListener(this);
        
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
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
    /**
     * Método que elimina una tabla obtenida por medio de la vista.
     */
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
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        this.ventanaElimTabla.cerrarVentana(ventanaElimTabla);
    }
    /**
     * mustra la ventana de la vista
     */
    public void mostrarVentana(){
        this.ventanaElimTabla.mostrarVentana(ventanaElimTabla);
    }
    
}
