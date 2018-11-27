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
abstract interface TablaDAO {
    /**
     * Crea un socket que pasa los parámetros del método al servidor para que este pueda devolver 
     * las bases de datos que a las que tiene acceso un usuario
     * @param pNombre Nombre de cuenta del usuario
     * @param pContraseña Contraseña del usuario
     * @return Retorna un String con todas las bases de datos a las que el usuario tiene acceso
     */
    abstract String getBases(String pNombre, String pContraseña);
    /**
     * Crea un socket que solicita al  servidor que pase todos los datos necesarios para generar un html
     * @param pBase Base de datos de la cual se quiere generar el html
     * @return Retorna un String con todos los datos para generar el html
     */
    abstract String generarHTML(String pBase);
}
