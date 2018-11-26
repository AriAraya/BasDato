/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author U1
 */
public class Escritor {
    public String direccion="";
    public String nombre="";
    public String contenido="";
    public File file=null;
    public Escritor(String pDireccion,String pNombre){
        direccion=pDireccion;
        nombre=pNombre;
        file=new File(direccion+nombre);
    }
    public boolean escribir(){
        if(file.exists()&&esTexto()){
            return true;
        }else{
            return false;
        }
    }
    public boolean leer(){
        if(file.exists()&&esTexto()){
            try {
                Scanner scanner;
                scanner = new Scanner(file);
                String linea="";
                while(scanner.hasNextLine()){
                    linea=scanner.nextLine();
                    contenido+=linea;
                    contenido+="\n";
                }
                return true;
            } catch (FileNotFoundException e) {
                return false;
            }
        }else{
            contenido="";
            return false;
        }
    }
    private boolean esTexto(){
        return (file.isFile()&&nombre.endsWith(".txt"))||(file.isFile()&&nombre.endsWith(".csv"));
    }
    public String getContenido(){
        return contenido;
    }
}
