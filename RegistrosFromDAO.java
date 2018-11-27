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
public interface RegistrosFromDAO {
    /**
     * Crea un socket que pasa la base de datos al servidor para que este devuelva las tablas disponibles
     * @param pBase Nombre de la base de datos que se va a acceder
     * @return Retorna un String con todas las tablas que estan registradas en una base de datos
     */
    abstract String getTablas(String pBase);
    /**
     * Crea un socket que pasa los parámetros del método al servidor para añadir un registro a una tabla
     * @param pTabla Nombre de la tabla donde se va a crear el registro
     * @param pBase Nombre de la base de datos con la que se va a trabajar
     * @param pDatos Datos que van a ser añadidos en el registro
     * @return Retorna un String que puede ser true, en caso de añadir el registro, o false en el caso 
     * contrario
     */
    abstract String añadirRegistro(String pTabla, String pBase, String pDatos);
    /**
     * Crea un socket para que el servidor pueda devolverle el formato que tienen los campos de una tabla
     * @param pBase Nombre de la base de datos
     * @param pTabla Nombre de la tabla a consultar el formato de los campos
     * @return Retorna un String con el formato de los campos
     */
    abstract String getFormato(String pBase, String pTabla);
}
