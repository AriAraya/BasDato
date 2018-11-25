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
    abstract String crearTabla(String pNombre,String pBase,String pCampo,Boolean pRequerido,int pTipo);
}
