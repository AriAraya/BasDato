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

public class CreaTablaDAOImpl implements CreaTablaDAO{
    private final int PUERTO = 5000;
    /**
     * Método que crea un socket y pasa los parámetros para que sean procesador por el servidor.
     * @param pNombre nombre de la tabla
     * @param pBase base donde se va a crear la tabla
     * @param pTextoCampos campos que se van a agregar en la tabla
     * @return el servidor devuelve un String que puede ser true, en caso de crear la tabla  o
     * false, en caso de no poder crear la tabla
     */
    @Override
    public String crearTabla(String pNombre,String pBase,String pTextoCampos){
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
            System.out.println("4,"+pNombre+","+pBase+pTextoCampos);
            out.writeUTF("4,"+pNombre+","+pBase+pTextoCampos);
            msg = in.readUTF();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
