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
public class ControladorElimRegistros implements ActionListener{
    private ElimRegistros ventanaElimR;
    private ElimRegistrosDAO dao;
    private String[] listaTablas;
    private String base;
    private String nombreTabla;
    private String[] listaFormato;
    
    public ControladorElimRegistros(ElimRegistros pVentana){
        ventanaElimR = pVentana;
        dao = new ElimRegistrosDAOImpl();
        
        base = ventanaElimR.base;
        listaTablas = ("\n"+dao.getTablas(base)).split("\n");
        ventanaElimR.cargaTablas(listaTablas);
        
        ventanaElimR.btEliminar.addActionListener(this);
        ventanaElimR.btVolver.addActionListener(this);
        ventanaElimR.btSeleccionar.addActionListener(this);
        ventanaElimR.boxTabla.addActionListener(this);
        ventanaElimR.boxCondicion1.addActionListener(this);
        ventanaElimR.boxCondicion2.addActionListener(this);
        
    }
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e acción dada por la vista
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Eliminar":
                eliminar();
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
    public void eliminar(){
        long startTime = System.currentTimeMillis();
        String cond1 = ventanaElimR.condicion1Seleccionada();
        String cond2 = ventanaElimR.condicion2Seleccionada();
        String msg = "";
        System.out.println(cond1.equals(" ") +" y "+ventanaElimR.txtRegistro.getText().isEmpty());
        if(cond1.equals(" ") && ventanaElimR.txtRegistro.getText().isEmpty()){
            msg = dao.elim1(base, nombreTabla);
            if(msg.equals("Tabla no encontrada")){
                JOptionPane.showMessageDialog(ventanaElimR, "La tabla no fue encontrada");
            }
            else if(msg.equals("Base de datos no encontrada")){
                JOptionPane.showMessageDialog(ventanaElimR, "La base de datos no fue encontrada");
            }
            else{
                JOptionPane.showMessageDialog(ventanaElimR, "Los registros fueron borrados exitosamente");
                long totalSum = (System.currentTimeMillis()-startTime);
                totalSum = totalSum/1000;
                ventanaElimR.jTextArea1.setText(msg+"\n Tiempo de ejecución: "+totalSum+" segundos");
            }
        }
        else{
            System.out.println("caca");
        }
    }
    public void mostrarVentana(){
        ventanaElimR.setVisible(true);
    }
    public void cerrar(){
        ventanaElimR.setVisible(false);
    }
    /**
     * Método que muestra el formato de los campos de una tabla
     */
    public void mostrarFormato(){
        ventanaElimR.jLTexto.setText("");
        nombreTabla = ventanaElimR.seleccionarTabla();
        String formato = "";
        if(!nombreTabla.isEmpty()){
            listaFormato = dao.getFormato(base, nombreTabla).split("-");
            ventanaElimR.cargarCampos(listaFormato);
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
            ventanaElimR.jLTexto.setText(formato);
        }
        else{
            JOptionPane.showMessageDialog(ventanaElimR, "Seleccione una base de datos válida");
        }
        
    }
}
