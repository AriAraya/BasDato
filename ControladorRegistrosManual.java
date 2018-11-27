/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Wall-E
 */
public class ControladorRegistrosManual implements ActionListener{
    private RegistrosManual ventanaRegistros;
    private RegistrosManualDAO dao;
    private String[] listaTablas;
    private String base;
    private String nombreTabla;
    
    public ControladorRegistrosManual(RegistrosManual ventana){
        ventanaRegistros = ventana;
        dao = new RegistrosManualDAOImpl();
        
        base = ventanaRegistros.base;
        listaTablas = ("\n"+dao.getTablas(base)).split("\n");
        ventanaRegistros.cargaTablas(listaTablas);
        
        this.ventanaRegistros.btAceptar.addActionListener(this);
        this.ventanaRegistros.btVolver.addActionListener(this);
        this.ventanaRegistros.btSeleccionarTabla.addActionListener(this);
        this.ventanaRegistros.boxTabla.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                añadirRegistro();
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
    
    public void añadirRegistro(){
        String datos = ventanaRegistros.txtDatos.getText();
        String msg = "";
        if(!datos.isEmpty()){
            if(!ventanaRegistros.jLformato.getText().isEmpty()){
                msg = dao.añadirRegistro(nombreTabla, base, datos);
                if(msg.equals("false")){
                    JOptionPane.showMessageDialog(ventanaRegistros, "No se pudo agregar el registro");
                }
                else{
                    JOptionPane.showMessageDialog(ventanaRegistros, "Se agregó el registro exitosamente");
                    cerrar();
                }
            }
            else{
                JOptionPane.showMessageDialog(ventanaRegistros, "No se seleccionó la tabla");
            }
        }
        else{
            JOptionPane.showMessageDialog(ventanaRegistros, "Ingrese los datos");
        }
    }  
    public void mostrarFormato(){
        nombreTabla = ventanaRegistros.seleccionarTabla();
        String formato = "";
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
        ventanaRegistros.jLformato.setText(formato);
    }
    public void mostrarVentana(){
        ventanaRegistros.setVisible(true);
    }
    public void cerrar(){
        ventanaRegistros.setVisible(false);
    }

    
}
