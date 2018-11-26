/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Wall-E
 */

public class ControladorCreaTabla implements ActionListener{
    private CreaTabla ventanaCreaTabla;
    private CreaTablaDAO dao;
    private String nombre;
    private String campo;
    private boolean requerido;
    private String tipo;
    private String base;
    private int contador = 1;
    public String textoCampos="";
    
    public ControladorCreaTabla(CreaTabla pVentana, String pBase){
        ventanaCreaTabla = pVentana;
        base = pBase;
        dao = new CreaTablaDAOImpl();
        
        this.ventanaCreaTabla.ChckBxRequerido.addActionListener(this);
        this.ventanaCreaTabla.ComboTipo.addActionListener(this);
        this.ventanaCreaTabla.btnCreaCampo.addActionListener(this);
        this.ventanaCreaTabla.btnVolver.addActionListener(this);
        this.ventanaCreaTabla.btAceptar.addActionListener(this);
        this.ventanaCreaTabla.txtFieldNombreCampo.addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Agregar campo":
                agregarCampo();
                break;
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
        nombre = ventanaCreaTabla.txtFieldNombre.getText();
        String msg = "";
        int pTipo=1;
        if(!nombre.isEmpty()){
            switch(tipo){
            case "int":
                pTipo=1;
                break;
            case "float":
                pTipo=2;
                break;
            case "String":
                pTipo=4;
                break;
            case "boolean":
                pTipo=3;
                break;
            default:
                break;
            }
            if(contador>=2){
                String[] listaCampos = textoCampos.split("//*");
                textoCampos = "";
                for(String lista1:listaCampos){
                    textoCampos+=lista1;
                }
                msg = dao.crearTabla(nombre,base,textoCampos);
                if(msg.equals("false")){
                    JOptionPane.showMessageDialog(ventanaCreaTabla, "No se puede crear la tabla.");
                }
                else{
                    JOptionPane.showMessageDialog(ventanaCreaTabla, "La tabla y el campo se crearon con exito.");
                    cerrar();
                }
            }
            else{
                JOptionPane.showMessageDialog(ventanaCreaTabla,"Para crear la tabla deben existir al menos dos tablas");
            }
            
        }
    }
    public void agregarCampo(){
        requerido=ventanaCreaTabla.isCheckSelected();
        campo=ventanaCreaTabla.txtFieldNombreCampo.getText();
        tipo=ventanaCreaTabla.seleccionarTipo();
        agregarTexto(campo,tipo,requerido);
    }
    public void cerrar(){
        ventanaCreaTabla.setVisible(false);
    }
    public void mostrarVentana(){
        ventanaCreaTabla.setVisible(true);
    }
    public void agregarTexto(String nombreC, String pTipo,boolean req){
        String msg = "Campo "+contador+++"[";
        msg+=nombreC+", "+pTipo+", ";
        if(req){
            msg+="Sí]";
        }else{
            msg+="No]";
        }
        if(pTipo.equals("String")){
            pTipo = "0";
        }
        else if(pTipo.equals("int")){
            pTipo = "1";
        }
        else if(pTipo.equals("float")){
            pTipo = "2";
        }
        else if(pTipo.equals("boolean")){
            pTipo = "3";
        }
        textoCampos += ", ,"+pTipo+","+nombreC+","+req+"*";
        ventanaCreaTabla.agregarTexto(msg);
    }
}
