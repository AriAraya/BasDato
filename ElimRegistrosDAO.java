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
public interface ElimRegistrosDAO {
    abstract String getTablas(String pBase);
    abstract String getFormato(String pBase, String pTabla);
    abstract String elim1(String pBase, String pTabla);
    abstract String elim2(String pBase, String pTabla);
}
