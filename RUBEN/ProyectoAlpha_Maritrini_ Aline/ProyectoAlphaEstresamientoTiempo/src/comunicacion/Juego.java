/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.Serializable;

/**
 *
 * @author alineitzelbecerracarranza
 */

public class Juego implements Serializable {
    private String direccionIPH;
    private int puertoTCP;
    private String direccionMul;
    private int puertoMul;
    private int numeroCeldas;

    public Juego(String direccionIPH, int puertoTCP, String direccionMul, int puertoMul) {
        this.direccionIPH = direccionIPH;
        this.puertoTCP = puertoTCP;
        this.direccionMul = direccionMul;
        this.puertoMul = puertoMul;
        this.numeroCeldas=12;
    }

    public String getDireccionIPH() {
        return direccionIPH;
    }

    public void setDireccionIPH(String direccionIPH) {
        this.direccionIPH = direccionIPH;
    }

    public int getPuertoTCP() {
        return puertoTCP;
    }

    public void setPuertoTCP(int puertoTCP) {
        this.puertoTCP = puertoTCP;
    }

    public String getDireccionMul() {
        return direccionMul;
    }

    public void setDireccionMul(String direccionMul) {
        this.direccionMul = direccionMul;
    }

    public int getPuertoMul() {
        return puertoMul;
    }

    public void setPuertoMul(int puertoMul) {
        this.puertoMul = puertoMul;
    }

    public int getNumeroCeldas() {
        return numeroCeldas;
    }

    public void setNumeroCeldas(int numeroCeldas) {
        this.numeroCeldas = numeroCeldas;
    }

    @Override
    public String toString() {
        return "Juego{" + "direccionIPH=" + direccionIPH + ", puertoTCP=" + puertoTCP + ", direccionMul=" + direccionMul + ", puertoMul=" + puertoMul + '}';
    }

    
}
