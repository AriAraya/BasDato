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
  
abstract interface CreaTablaDAO {
    /**
     * Método que crea un socket y pasa los parámetros para que sean procesador por el servidor.
     * @param pNombre nombre de la tabla
     * @param pBase base donde se va a crear la tabla
     * @param pTextoCampos campos que se van a agregar en la tabla
     * @return el servidor devuelve un String que puede ser true, en caso de crear la tabla  o
     * false, en caso de no poder crear la tabla
     */
    abstract String crearTabla(String pNombre,String pBase,String pTextoCampos);
}
