/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

/**
 *
 * @author U1
 */
public class BasDato {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Escritor este=new Escritor("C:/Users/U1/Desktop/Archivos/Juegos/","Horas juegos.txt");
        System.out.print(este.leer());
        System.out.print(este.getContenido());
    }    
}
