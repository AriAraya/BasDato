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
abstract interface CrearBaseDAO {
    /**
     * Crea un socket para que pase el nombre de la base de datos a crear al servidor
     * @param pNombre nombre de la base de datos
     * @return retorna un String que puede ser true, en caso de crear la base de datos, o
     * false en el caso de que no haya podidio crear la base de datos
     */
    abstract String crearBase(String pNombre);
}
