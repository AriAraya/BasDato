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
abstract interface ElimTablaDAO {
    /**
     * Crea us socket, el cual pasa los parámetros del método al servidor para eliminar una tabla
     * @param pNombre Nombre de la tabla a eliminar
     * @param pBase Nombre de la base de datos donde se encuentra la tabla a eliminar
     * @return Un String dado por el servidor que puede ser true, en caso de haber eliminado la tabla, o
     * false, en caso de no poder eliminar la tabla.
     */
    abstract String eliminaTabla(String pNombre, String pBase);
    /**
     * Crea un socket, el cual pasa la base de datos para que el servidor devuelva un String con las 
     * tablas que están cargadas por el servidor
     * @param pBase Nombre de la base de datos donde se quieren ver las tablas
     * @return retorna un String con las tablas que se encuentran en la base de datos
     */
    abstract String cargaTablas(String pBase);
}
