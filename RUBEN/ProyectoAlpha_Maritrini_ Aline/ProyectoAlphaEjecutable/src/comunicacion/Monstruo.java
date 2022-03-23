/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

/**
 *
 * @author alineitzelbecerracarranza
 */
public class Monstruo {
    private int celda;

    public Monstruo(int celda) {
        this.celda = celda;
    }

    @Override
    public String toString() {
        return "Monstruo" + " "+celda;
    }

}
