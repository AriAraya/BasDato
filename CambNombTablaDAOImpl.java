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
 * @author U1
 */
public class CambNombTablaDAOImpl implements CambNombTablaDAO{
    private final int PUERTO = 5000;
    /**
     * Método que crea una solicitud que es enviada a través de un socket a un servidor, el cual
     * toma el nombre actual de la tabla, el nuevo nombre de la tabla y la base donde está esta
     * tabla y cambia el nombre, devolviendo un String que indica si cambió o no el nombre de la 
     * tabla.
     * @param pTabla Nombre actual de la tabla a modificar
     * @param pNuevoNombre Nuevo nombre que se le va a asignar a la tabla
     * @param pBase Base en la cual se encuentra la tabla a modificar
     * @return retorna un String que puede ser true, en caso que cambió el nombre de la tabla, o
     * false, en el caso de no haber cambiado el nombre
     */
    @Override
    public String cambiarNombre(String pTabla,String pNuevoNombre,String pBase) {
        String msg="";
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción, el nombre de usuario y la contraseña para verificar
            out.writeUTF("9,"+pTabla+","+pNuevoNombre+","+pBase);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Obtiene las tablas que pueden ser modificadas por medio de un socket
     * @param pBase nombre de la base de datos en la que se va a trabajar
     * @return retorna un String con las tablas que contiene la base de datos indicada
     */
    @Override
    public String getTablas(String pBase) {
        String msg="";
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción, el nombre de usuario y la contraseña para verificar
            out.writeUTF("0,0,"+pBase);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
