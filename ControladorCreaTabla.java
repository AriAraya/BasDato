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

public class ControladorCreaTabla implements ActionListener{
    private CreaTabla ventanaCreaTabla;
    private CreaTablaDAO dao;
    private String nombre;
    private String campo;
    private boolean requerido;
    private String tipo;
    private String base;
    private static int contador = 0;
    
    public ControladorCreaTabla(CreaTabla pVentana, String pBase){
        ventanaCreaTabla = pVentana;
        base = pBase;
        dao = (CreaTablaDAO) new CreaTablaDAOImpl();
        
        this.ventanaCreaTabla.ChckBxRequerido.addActionListener(this);
        this.ventanaCreaTabla.ComboTipo.addActionListener(this);
        this.ventanaCreaTabla.btnCreaCampo.addActionListener(this);
        this.ventanaCreaTabla.btnVolver.addActionListener(this);
        this.ventanaCreaTabla.txtFieldNombreCampo.addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Agregar campo":
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
        requerido=ventanaCreaTabla.ChckBxRequerido.isContentAreaFilled();
        campo=ventanaCreaTabla.txtFieldNombreCampo.getText();
        tipo=ventanaCreaTabla.seleccionarTipo();
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
            msg = dao.crearTabla(nombre,base,campo,requerido,pTipo);
            if(msg.equals("false")){
                JOptionPane.showMessageDialog(ventanaCreaTabla, "No se puede crear la tabla.");
            }
            else{
                JOptionPane.showMessageDialog(ventanaCreaTabla, "La tabla y el campo se crearon con exito.");
                agregarTexto(campo,tipo,requerido);
            }
        }
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
        ventanaCreaTabla.agregarTexto(msg);
    }
}
