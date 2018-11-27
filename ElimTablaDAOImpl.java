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
public class ElimTablaDAOImpl implements ElimTablaDAO{
    private final int PUERTO = 5000;
    /**
     * Crea us socket, el cual pasa los parámetros del método al servidor para eliminar una tabla
     * @param pNombre Nombre de la tabla a eliminar
     * @param pBase Nombre de la base de datos donde se encuentra la tabla a eliminar
     * @return Un String dado por el servidor que puede ser true, en caso de haber eliminado la tabla, o
     * false, en caso de no poder eliminar la tabla.
     */ 
    @Override
    public String eliminaTabla(String pNombre, String pBase) {
        String nombre = pNombre;
        String base = pBase;
        String msg = "";
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción, el nombre de usuario y la contraseña para verificar
            out.writeUTF("7,"+nombre+","+base);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Crea un socket, el cual pasa la base de datos para que el servidor devuelva un String con las 
     * tablas que están cargadas por el servidor
     * @param pBase Nombre de la base de datos donde se quieren ver las tablas
     * @return retorna un String con las tablas que se encuentran en la base de datos
     */
    @Override
    public String cargaTablas(String pBase) {
        String msg = "";
        String base = pBase;
        try{
            Socket client = new Socket("localhost",PUERTO);
            //Manda mensajes al servidor
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            //Lee lo que manda el servidor
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            //Manda la acción, el nombre de usuario y la contraseña para verificar
            out.writeUTF("0,0,"+base);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
