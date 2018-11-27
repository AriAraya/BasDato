/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasDato;

/**
 *Interface que contiene los siguientes métodos
 * @author Wall-E
 */
abstract interface AnadirUserDAO {
    /**
     * Crea una solicitud para que sea enviada por socket a un servidor, el cual retorna un String
     * de true o false que si es true, indica que el usuario fue creado por el servidor, si el false
     * hubo un error al crear el usuario
     * @param pNombre Nombre del usuario a registrar
     * @param pConstrasenia Contraseña del usuario
     * @param pBasesDeDatos bases de datos a las cuales va a tener acceso el usuario a registrar
     * @return String que indica si el usuario se registró o no
     */
    abstract String anadirUser(String pNombre, String pConstrasenia, String pBasesDeDatos);
    /**
     * Obtiene todas las bases de datos que han sido registradas en el servidor
     * @return String de todas las bases de datos registradas por del servidos
     */
    abstract String getBasesDeDatos();
}
