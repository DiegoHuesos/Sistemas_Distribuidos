/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesExtra;

/**
 *
 * @author ruben
 */
public class Reservacion {
    private String id;
    private double cost;
    
    public Reservacion(String id, double cost){
        this.id=id;
        this.cost=cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
}
