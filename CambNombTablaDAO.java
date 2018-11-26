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
abstract public interface CambNombTablaDAO{
    abstract String cambiarNombre(String pTabla,String pNuevoNombre,String pBase);
}