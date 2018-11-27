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
public class RegistrosManualDAOImpl implements RegistrosManualDAO{
    private final int PUERTO = 5000;
    /**
     * Crea un socket que pasa la base de datos al servidor para que este devuelva las tablas disponibles
     * @param pBase Nombre de la base de datos que se va a acceder
     * @return Retorna un String con todas las tablas que estan registradas en una base de datos
     */
    @Override
    public String getTablas(String pBase) {
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
            out.writeUTF("0,0,"+pBase);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Crea un socket que pasa los parámetros del método al servidor para añadir un registro a una tabla
     * @param pTabla Nombre de la tabla donde se va a crear el registro
     * @param pBase Nombre de la base de datos con la que se va a trabajar
     * @param pDatos Datos que van a ser añadidos en el registro
     * @return Retorna un String que puede ser true, en caso de añadir el registro, o false en el caso 
     * contrario
     */
    @Override
    public String añadirRegistro(String pTabla, String pBase, String pDatos) {
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
            out.writeUTF("5,"+pTabla+","+pBase+","+pDatos);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Crea un socket para que el servidor pueda devolverle el formato que tienen los campos de una tabla
     * @param pBase Nombre de la base de datos
     * @param pTabla Nombre de la tabla a consultar el formato de los campos
     * @return Retorna un String con el formato de los campos
     */
    @Override
    public String getFormato(String pBase, String pTabla) {
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
            out.writeUTF("0,1,"+pBase+","+pTabla);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
