/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import estres.Globals;
import java.util.*;

/**
 *
 * @author jcsiglerp
 */
public class Juego {
    private Set<String> usuarios;
    private Map<String, Integer> puntuacion;
    private final int POINTS_TO_WIN = Globals.point_to_win;
    
    private int n; // Dimensiones del tablero
    private int x; // Posicion actual del topo 
    private boolean topoGolpeado;
    
    private boolean finalizado = false;
    private String winner;
    
    public Juego(int n) {
        this.n = n;
        this.x = 0;
        this.topoGolpeado = true;
        this.usuarios = new HashSet<>();
        this.puntuacion = new HashMap<>();
    }
    
    public Set<String> getUsuarios(){
        return usuarios;
    }
    
    public boolean getStatus() {
        return finalizado;
    }
    
    public void setStatus(boolean status) {
        this.finalizado = status;
    }
    
    public String getWinner() {
        return winner;
    }
    
    public boolean agregaJugador(String name) {
        if (usuarios.contains(name)) return false;
        usuarios.add(name);
        puntuacion.put(name, 0);
        return true;
    }
    
    public void eliminaJugador(String name) {
        usuarios.remove(name);
        puntuacion.remove(name);
    }
        
    // Posicion aleatoria
    public void mueveTopo() {
        Random rand = new Random();
        x = rand.nextInt(n);
        topoGolpeado = false;
    }
    
    public int obtenPosicion() {
        return x;
    }
    
    // El jugador golpeo en la casilla (i,j)
    // Regresa la puntuacion del jugador
    public synchronized int golpeJugador(String name, int i) {
        int curr = puntuacion.get(name);
        if (finalizado || topoGolpeado) return curr;
        if (x != i) return curr; // No le atino
        topoGolpeado = true;
        curr ++;
        puntuacion.put(name, curr);
        if (curr >= POINTS_TO_WIN) {
            finalizado = true;
            winner = name;          
        }
        return curr;
    }
    
    public int obtenPuntuacion(String name) {
        return puntuacion.get(name);
    }
    
    public void restart() {
        usuarios.clear();
        puntuacion.clear();
        winner = null;
        finalizado = false;
    }
}
