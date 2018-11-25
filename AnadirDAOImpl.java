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
        
        return msg;
    }
    
}
