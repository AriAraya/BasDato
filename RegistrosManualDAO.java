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
abstract interface RegistrosManualDAO {
    abstract String getTablas(String pBase);
    abstract String añadirRegistro(String pTabla, String pBase, String pDatos);
    abstract String getFormato(String pBase, String pTabla);
}
