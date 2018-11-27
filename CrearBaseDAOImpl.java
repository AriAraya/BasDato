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
public class CrearBaseDAOImpl implements CrearBaseDAO{
    private final int PUERTO = 5000;
    /**
     * Crea un socket para que pase el nombre de la base de datos a crear al servidor
     * @param pNombre nombre de la base de datos
     * @return retorna un String que puede ser true, en caso de crear la base de datos, o
     * false en el caso de que no haya podidio crear la base de datos
     */
    @Override
    public String crearBase(String pNombre) {
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
            out.writeUTF("3,"+pNombre);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    
}
