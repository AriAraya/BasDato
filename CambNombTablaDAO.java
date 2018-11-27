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
    /**
     * Método que crea una solicitud que es enviada a través de un socket a un servidor, el cual
     * toma el nombre actual de la tabla, el nuevo nombre de la tabla y la base donde está esta
     * tabla y cambia el nombre, devolviendo un String que indica si cambió o no el nombre de la 
     * tabla.
     * @param pTabla Nombre actual de la tabla a modificar
     * @param pNuevoNombre Nuevo nombre que se le va a asignar a la tabla
     * @param pBase Base en la cual se encuentra la tabla a modificar
     * @return retorna un String que puede ser true, en caso que cambió el nombre de la tabla, o
     * false, en el caso de no haber cambiado el nombre
     */
    abstract String cambiarNombre(String pTabla,String pNuevoNombre,String pBase);
    /**
     * Obtiene las tablas que pueden ser modificadas por medio de un socket
     * @param pBase nombre de la base de datos en la que se va a trabajar
     * @return retorna un String con las tablas que contiene la base de datos indicada
     */
    abstract String getTablas(String pBase);
}