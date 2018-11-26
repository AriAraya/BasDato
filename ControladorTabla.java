/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wall-E
 */
public class ControladorTabla implements ActionListener{
    private Tabla ventanaTablas;
    private TablaDAO dao;
    private String[] listaBases;
    
    public ControladorTabla(Tabla ventana){
        ventanaTablas = ventana;
        dao = new TablaDAOImpl();
        listaBases = ventanaTablas.bases.split(",");
        
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
        CreaTabla ventanaCreaTabla = new CreaTabla();
        ControladorCreaTabla controlador = new ControladorCreaTabla(ventanaCreaTabla, base);
        controlador.mostrarVentana();
        cerrar();
    }
    public void cerrar(){
        ventanaTablas.cerrarVentana(ventanaTablas);
    }
    public void mostrarVentana(){
        ventanaTablas.mostrarVentata(ventanaTablas);
    }
}
