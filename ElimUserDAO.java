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
abstract interface ElimUserDAO {
    /**
     * Crea un socket que pasa el nombre del usuario al servidor para ser eliminado
     * @param pNombre Nombre del usuario a eliminar
     * @return retorna un String que puede ser true, si logra eliminar al usuario, o 
     * un false, si no logra eliminarlo.
     */
    abstract String removeUser(String pNombre);
    /**
     * Crea un socker que pide al servidor los usuarios registrados
     * @return retorna un String con todos los nombres de usuario registrados por ek servidor
     */
    abstract String getUsers();
}
