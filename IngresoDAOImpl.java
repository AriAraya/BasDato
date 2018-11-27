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


public class IngresoDAOImpl implements IngresoDAO {
    private final int PUERTO=5000;
    /**
     * Crea un socket que pasa los parámetros del método para que este pueda validar el ingreso al
     * programa
     * @param nombre Nombre de usuario a verificar
     * @param contraseña Contraseña del usuario a verificar
     * @return retorna un String que puede ser vacío, en caso que no exista el usuario consultado, o
     * un String con todas las bases de datos a las que tiene acceso dicho usuario
     */
    @Override
    public String validaIngreso(String nombre, String contraseña) {
        String mensaje = "";
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
            mensaje = in.readUTF();
        }
        catch (IOException e) {
            mensaje = "fallo";
            e.printStackTrace();
        }
        return mensaje;
    }
    /**
     * Crea un socket que pide al servidor todas las bases de datos que han sido registradas
     * @return Retorna un String con todas las bases de datos registradas
     */
    @Override
    public String getBasesDeDatos() {
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
        out.writeUTF("0,4");
        msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
        return msg;
    }
    
}
