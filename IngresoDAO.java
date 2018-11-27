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
abstract interface IngresoDAO {
    /**
     * Crea un socket que pasa los parámetros del método para que este pueda validar el ingreso al
     * programa
     * @param nombre Nombre de usuario a verificar
     * @param contraseña Contraseña del usuario a verificar
     * @return retorna un String que puede ser vacío, en caso que no exista el usuario consultado, o
     * un String con todas las bases de datos a las que tiene acceso dicho usuario
     */
    abstract String validaIngreso(String nombre, String contraseña);
    /**
     * Crea un socket que pide al servidor todas las bases de datos que han sido registradas
     * @return Retorna un String con todas las bases de datos registradas
     */
    abstract String getBasesDeDatos();
}
