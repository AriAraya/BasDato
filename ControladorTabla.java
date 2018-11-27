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
public class ControladorTabla implements ActionListener{
    private Tabla ventanaTablas;
    private TablaDAO dao;
    private String[] listaBases;
    private String accion;
    
    public ControladorTabla(Tabla ventana){
        ventanaTablas = ventana;
        dao = new TablaDAOImpl();
        listaBases = ventanaTablas.bases.split("[\n,]");
        accion = ventanaTablas.accion;
        this.ventanaTablas.cargarBases(listaBases);
        
        this.ventanaTablas.btAceptar.addActionListener(this);
        this.ventanaTablas.btCancelar.addActionListener(this);
        this.ventanaTablas.boxBases.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                mostrarSigVentana();
                break;
            case "Volver":
                cerrar();
                break;
            default:
                break;
        }
    }
    public void mostrarSigVentana(){
        String base = ventanaTablas.seleccionarBase();
        if(!base.equals("")){
            if(accion.equals("crear")){
                
                CreaTabla ventanaCreaTabla = new CreaTabla();
                ControladorCreaTabla controlador = new ControladorCreaTabla(ventanaCreaTabla, base);
                controlador.mostrarVentana();
                cerrar();
            }
            else if(accion.equals("eliminar")){
                ElimTabla ventanaElimTabla = new ElimTabla(base);
                ControladorElimTabla controlador = new ControladorElimTabla(ventanaElimTabla);
                controlador.mostrarVentana();
                cerrar();
            }
            else if(accion.equals("cambiar")){
                CambNombTabla ventanaCambiar = new CambNombTabla(base);
                ControladorCambNombTabla controlador = new ControladorCambNombTabla(ventanaCambiar);
                controlador.mostrarVentana();
                cerrar();
            }
            else if(accion.equals("registroM")){
                RegistrosManual ventanaRegistro = new RegistrosManual(base);
                ControladorRegistrosManual controlador = new ControladorRegistrosManual(ventanaRegistro);
                controlador.mostrarVentana();
            }
        }
        else{
            JOptionPane.showMessageDialog(ventanaTablas, "Seleccione una base válida");
        }
    }
    public void cerrar(){
        ventanaTablas.cerrarVentana(ventanaTablas);
    }
    public void mostrarVentana(){
        ventanaTablas.mostrarVentata(ventanaTablas);
    }
}
