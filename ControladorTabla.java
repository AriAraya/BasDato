/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final String PATH = "C:\\Users\\monge\\Desktop\\reporte.html";
    /**
     * Contructor del controlador para seleccionar una base de datos
     * @param ventana ventana en la cual se va a trabajar
     */
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
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
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
    /**
     * Método que verifica para qué es la base de datos que se está seleccionando
     */
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
                cerrar();
            }
            else if(accion.equals("HTML")){
                String msg = dao.generarHTML(base);
                toHTML(msg);
                cerrar();
            }
            else if(accion.equals("RegistrosFrom")){
                RegistrosFrom ventanaRegistroF = new RegistrosFrom(base);
                ControladorRegistrosFrom controlador = new ControladorRegistrosFrom(ventanaRegistroF);
                controlador.mostrarVentana();
                cerrar();
            }
            else if(accion.equals("eliminarRegistros")){
                ElimRegistros ventanaElimRegistro = new ElimRegistros(base);
                ControladorElimRegistros controlador = new ControladorElimRegistros(ventanaElimRegistro);
                controlador.mostrarVentana();
                cerrar();
            }
        }
        else{
            JOptionPane.showMessageDialog(ventanaTablas, "Seleccione una base válida");
        }
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaTablas.cerrarVentana(ventanaTablas);
    }
    /**
     * muestra la ventana de la vista
     */
    public void mostrarVentana(){
        ventanaTablas.mostrarVentata(ventanaTablas);
    }
    /**
     * Método que toma un String del modelo y lo cambia de tal forma que se pueda crear un archivo .html
     * @param datos Datos que van a ir dentro del html
     */
    public void toHTML(String datos){
        String[] listaDatos = datos.split("[\n]");
        datos = "<HTML><HEAD><TITLE>Base de datos: "+listaDatos[0]+"</TITLE></HEAD><BODY>";
        datos+="<h1 align='center'>"+listaDatos[0]+"</h1><br/><br/><pre>";
        for(int i = 1;i<listaDatos.length;i++){
            datos+=listaDatos[i]+"</br></br>";
        }
        datos+="</pre><br/><br/><br/><p align='right'>Integrantes:<br/>Ariel Araya<br/>Edgar Mata<br/>Walter López</p></BODY></HTML>";
        creaArchivo(datos);
        JOptionPane.showMessageDialog(ventanaTablas, "Reporte generado exitosamente.\n Generado en: "+PATH);
    }
    /**
     * Crea un archivo en un PATH definido
     * @param datos Datos que se van a escribir dentro de un archivo
     */
    public void creaArchivo(String datos){
        String ruta = PATH;
        File archivo = new File(ruta);
        BufferedWriter bw;
        try{
            if(archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(datos);
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(datos);
            }
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(ventanaTablas, "No se pudo realizar el archivo");
        }
    }
}
