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
public class AnadirDAOImpl implements AnadirUserDAO{
    private final int PUERTO=5000;
    /**
     * Crea una solicitud para que sea enviada por socket a un servidor, el cual retorna un String
     * de true o false que si es true, indica que el usuario fue creado por el servidor, si el false
     * hubo un error al crear el usuario
     * @param pNombre Nombre del usuario a registrar
     * @param pConstrasenia Contraseña del usuario
     * @param pBasesDeDatos bases de datos a las cuales va a tener acceso el usuario a registrar
     * @return String que indica si el usuario se registró o no
     */
    @Override
    public String anadirUser(String pNombre, String pConstrasenia, String pBasesDeDatos) {
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
            out.writeUTF("2,"+pNombre+","+pConstrasenia+","+pBasesDeDatos);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    /**
     * Obtiene todas las bases de datos que han sido registradas en el servidor
     * @return String de todas las bases de datos registradas por del servidos
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
