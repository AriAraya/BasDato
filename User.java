/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author U1
 */
public class User {
    private ArrayList<String> basesPermitidas;
    private ArrayList<String> basesExistentes;
    private String entrada="";
    private boolean admin=false;
    private boolean aceptado=false;
    private String host="127.0.0.1";
    private int puerto=5000;
    private DataOutputStream c;
    private DataInputStream d;
    
    public String getEntrada(){
        return entrada;
    }
    public ArrayList<String> getBasesPermitidas(){
        return basesPermitidas;
    }
    public ArrayList<String> getBasesExistentes(){
        return basesExistentes;
    }
    public void ajusteEntrada(){
        entrada="";
    }
    public boolean valUser(String pNombre,String pContraseña){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("0,3"+pNombre+","+pContraseña);
            c.flush();
            d=new DataInputStream(sc.getInputStream());
            entrada=d.readUTF();
            sc.close();
            if(entrada!=""){
                //Aqui lo que tengo que hacer es meter la entrada en bases permitidas
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean pedirCampos(String pBase,String pTabla){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("0,1,"+pBase+","+pTabla);
            c.flush();
            d=new DataInputStream(sc.getInputStream());
            entrada=d.readUTF();
            sc.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean añadirRegistro(String pTabla, String pBase, String pEntrada){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("5,"+pTabla+","+pBase+","+pEntrada);
            c.flush();
            d=new DataInputStream(sc.getInputStream());
            entrada=d.readUTF();
            sc.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean pedirTablas(String pBase){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("0,0,"+pBase);
            c.flush();
            d=new DataInputStream(sc.getInputStream());
            entrada=d.readUTF();
            sc.close();
            return true;
        }catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean pedirUsuarios(){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("0,2");
            c.flush();
            d=new DataInputStream(sc.getInputStream());
            entrada=d.readUTF();
            sc.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean elimUser(String pNombre){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("1"+pNombre);
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean preguntarBases(){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("0,4");
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean añadirUsuario(String pNombre,String pContraseña,String pBases){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("2,"+pNombre+","+pContraseña+","+pBases);
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean crearBase(String pNombre){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("3,"+pNombre);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean crearTabla(String pNombre, String pBase,String pTipo,String pNombreCampo,String pRequerido){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("4,"+pNombre+","+pBase+", ,"+pTipo+","+pNombreCampo+","+pRequerido);
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean selecDatos(String pBase,String pTabla,String pNombresCampos){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("6,0"+pBase+","+pTabla+","+pNombresCampos);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean selecDatos(String pBase,String pTabla,String pComparado,String pCondicion,String pComparador,String pNombresCampos){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("6,1"+pBase+","+pTabla+","+pComparado+","+pCondicion+","+pComparador+","+pNombresCampos);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean elimTabla(String pBase,String pTabla){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("7"+pTabla+","+pBase);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean elimRegistros(String pBase,String pTabla,String pNombreCampo,String pCondicion, String pComparador){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("8,1,"+pBase+","+pTabla+","+pNombreCampo+","+pCondicion+","+pComparador);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean elimRegistros(String pBase,String pTabla){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("8,0,"+pBase+","+pTabla);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean cambNombreTabla(String pTabla, String pNuevoNombre,String pBase){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("9,"+pTabla+","+pNuevoNombre+","+pBase);
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean cargarRegistrosManual(String pEntrada,String pBase,String pTabla){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("a,"+pEntrada+","+pBase+","+pTabla);
            c.flush();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean ordenar(String pBase,String pTabla,String pCampo,String pCondicion){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("b,"+pBase+","+pTabla+","+pCampo+","+pCondicion);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean html(String pBase){
        try {
            Socket sc;
            sc=new Socket(host,puerto);
            c= new DataOutputStream(sc.getOutputStream());
            c.writeUTF("c,"+pBase);
            c.flush();
            entrada=d.readUTF();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}