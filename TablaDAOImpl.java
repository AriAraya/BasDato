/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Wall-E
 */
public class TablaDAOImpl implements TablaDAO{
    private final int PUERTO = 5000;
    String contraseña;
    String nombre;
    @Override
    /**
     * Crea un socket que pasa los parámetros del método al servidor para que este pueda devolver 
     * las bases de datos que a las que tiene acceso un usuario
     * @param pNombre Nombre de cuenta del usuario
     * @param pContraseña Contraseña del usuario
     * @return Retorna un String con todas las bases de datos a las que el usuario tiene acceso
     */
    public String getBases(String pNombre, String pContraseña) {
        String msg = "";
        nombre = pNombre;
        contraseña = pContraseña;
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción, el nombre de usuario y la contraseña para verificar
            out.writeUTF("0,3,"+nombre+","+contraseña);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Crea un socket que solicita al  servidor que pase todos los datos necesarios para generar un html
     * @param pBase Base de datos de la cual se quiere generar el html
     * @return Retorna un String con todos los datos para generar el html
     */
    @Override
    public String generarHTML(String pBase) {
        String msg="";
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción la servidor
            out.writeUTF("c,"+pBase);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
