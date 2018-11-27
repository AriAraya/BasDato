/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wall-E
 */
public class ControladorRegistrosFrom implements ActionListener{
    private RegistrosFrom ventanaRegistros;
    private RegistrosFromDAO dao;
    private String[] listaTablas;
    private String base;
    private String nombreTabla;
    private String rutatotal;
    private String datos;
    
    public ControladorRegistrosFrom(RegistrosFrom pVentana) {
        ventanaRegistros = pVentana;
        dao = new RegistrosFromDAOImpl();
        base = ventanaRegistros.base;
        listaTablas = ("\n"+dao.getTablas(base)).split("\n");
        ventanaRegistros.cargaTablas(listaTablas);
        
        this.ventanaRegistros.btInsertar.addActionListener(this);
        this.ventanaRegistros.btSeleccionar.addActionListener(this);
        this.ventanaRegistros.btVolver.addActionListener(this);
        this.ventanaRegistros.btSeleccionarTabla.addActionListener(this);
        this.ventanaRegistros.boxTablas.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                ingresarRegistros();
                break;
            case "Seleccionar archivo":
                seleccionarAchivo();
                break;
            case "Seleccionar tabla":
                mostrarFormato();
                break;
            case "Volver":
                cerrar();
                break;
            default:
                break;
        }
    }
    /**
     * Ingresa los datos obtenidos de un archivo en una tabla
     */
    public void ingresarRegistros(){
        leerArchivo();
        String valida = dao.añadirRegistro(nombreTabla, base, datos);
        if(valida.equals("false")){
            JOptionPane.showMessageDialog(ventanaRegistros, "No se pudo cargar los registros de: \n"+rutatotal);
        }
        else{
            JOptionPane.showMessageDialog(ventanaRegistros, "Archivo cargado y registrado con éxito");
            cerrar();
        }
    }
    public void leerArchivo(){
        FileReader f = null;
        datos = "";
        try {
            String cadena;
            f = new FileReader(rutatotal);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                datos+=cadena+";";
            }
            datos = datos.substring(0, datos.length()-1);
            System.out.println(datos);
            b.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(ventanaRegistros, "No se encontró el archivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ventanaRegistros, "No se pudo leer el archivo");
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(ControladorRegistrosFrom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Este método busca el archivo del cual se van a cargar los registros
     */
    public void seleccionarAchivo(){
        FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(ventanaRegistros, "Lista de Archivos desde Frame",FileDialog.LOAD);
        dialogoArchivo.setVisible(true);
        if(dialogoArchivo.getFile()!=null){ /* Validar que se haya Seleccionado un Archivo*/
           String directorio = dialogoArchivo.getDirectory();
           String nombreArchivo =dialogoArchivo.getFile(); 
           rutatotal = directorio + nombreArchivo;
           JOptionPane.showMessageDialog(ventanaRegistros,"Archivo seleccionado: "+rutatotal);
        }
        else
            JOptionPane.showMessageDialog(ventanaRegistros,"No Seleccionó Archivo");
        }
    /**
     * Método que muestra el formato de los campos de una tabla
     */
    public void mostrarFormato(){
        ventanaRegistros.jLTexto.setText("");
        nombreTabla = ventanaRegistros.seleccionarTabla();
        String formato = "";
        if(!nombreTabla.isEmpty()){
            String[] listaFormato = dao.getFormato(base, nombreTabla).split("-");
            for(int i = 0; i<listaFormato.length;i++){
                String[] listaCampos = listaFormato[i].split("[ ,]");
                formato+=listaCampos[2]+"(";
                if(listaCampos[2].equals("0")){
                    if(listaCampos[3].equals("true*")){
                        formato+="(String, Sí),";
                    }else{
                        formato+="(String, No),";
                    }
                }
                else if(listaCampos[2].equals("1")){
                    if(listaCampos[3].equals("true*")){
                        formato+="(entero, Sí),";
                    }else{
                        formato+="(entero, No),";
                    }
                }
                else if(listaCampos[2].equals("2")){
                    if(listaCampos[3].equals("true*")){
                        formato+="(buleano, Sí),";
                    }else{
                        formato+="(buleano, No),";
                    }
                }
                else if(listaCampos[2].equals("3")){
                    if(listaCampos[3].equals("true*")){
                        formato+="(flotante, Sí),";
                    }else{
                        formato+="(flotante, No),";
                    }
                }
            }
            ventanaRegistros.jLTexto.setText(formato);
        }
        else{
            JOptionPane.showMessageDialog(ventanaRegistros, "Seleccione una base de datos válida");
        }   
    }
    /**
     * Muestra la ventana de la vista
     */
    public void mostrarVentana(){
        ventanaRegistros.setVisible(true);
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaRegistros.setVisible(false);
    }
    
}
