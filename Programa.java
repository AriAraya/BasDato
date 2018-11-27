/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

/**
 *
 * @author Wall-E
 */
public class Programa {
    /**
     * main
     * @param args 
     */
    public static void main(String[] args){
        Ingreso ventana = new Ingreso();
        ControladorIngreso controlador = new ControladorIngreso(ventana);
        controlador.ventanaIngreso.setVisible(true);
        controlador.ventanaIngreso.setLocationRelativeTo( null );
    }
}
