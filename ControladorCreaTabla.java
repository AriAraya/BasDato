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
    /**
     * Contructor del controlador para crear tablas. Recibe la ventan de la vista a trabajar y la base
     * para cargar las tablas que contiene esta base
     * @param pVentana ventana de la vista con la cual se va a trabajar
     * @param pBase Base de datos, de la cual se van a obtener las tablas
     */
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
    
    /**
     * Obtiene la acción dada por la vista y realiza una operación dependiendo de la acción dada
     * @param e Acción dada por la vista
     */
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
    /**
     * Crea la tabla en una base de datos dada. Obtiene los datos de contantes de la vista y
     * estos datos los pasa al modelo
     */
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
    /**
     * Método que agrega los datos de los campos en una variable para despues crear la tabla con los datos
     */
    public void agregarCampo(){
        requerido=ventanaCreaTabla.isCheckSelected();
        campo=ventanaCreaTabla.txtFieldNombreCampo.getText();
        tipo=ventanaCreaTabla.seleccionarTipo();
        agregarTexto(campo,tipo,requerido);
    }
    /**
     * cierra la ventana de la vista
     */
    public void cerrar(){
        ventanaCreaTabla.setVisible(false);
    }
    /**
     * muestra la ventana de la vista
     */
    public void mostrarVentana(){
        ventanaCreaTabla.setVisible(true);
    }
    /**
     * Método que agrega los datos de los campos en un StringVar para que el cliente tenga noción de 
     * cómo están quedando los datos de los campos
     * @param nombreC nombre del campo
     * @param pTipo tipo de dato del campo
     * @param req boolean que verifica si el campo es requerido o no
     */
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
