/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.*;

/**
 *
 * @author jcsiglerp
 */
public class Juego {
    Set<String> usuarios;
    Map<String, Integer> puntuacion;
    int POINTS_TO_WIN = 3;
    
    int n; // Dimensiones del tablero
    int x; // Posicion actual del topo 
    boolean topoGolpeado;
    
    boolean finalizado = false;
    String winner;
    
    public Juego(int n) {
        this.n = n;
        this.x = 0;
        this.topoGolpeado = true;
        this.usuarios = new HashSet<>();
        this.puntuacion = new HashMap<>();
    }
    
    boolean agregaJugador(String name) {
        if (usuarios.contains(name)) return false;
        usuarios.add(name);
        puntuacion.put(name, 0);
        return true;
    }
    
    void eliminaJugador(String name) {
        usuarios.remove(name);
        puntuacion.remove(name);
    }
        
    // Posicion aleatoria
    void mueveTopo() {
        Random rand = new Random();
        x = rand.nextInt(n);
        topoGolpeado = false;
    }
    
    // El jugador golpeo en la casilla (i,j)
    // Regresa la puntuacion del jugador
    int golpeJugador(String name, int i) {
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
    
    int obtenPuntuacion(String name) {
        return puntuacion.get(name);
    }
    
    int obtenPosicion() {
        return x;
    }
    
    void restart() {
        usuarios.clear();
        puntuacion.clear();
        winner = null;
        finalizado = false;
    }
    
}
