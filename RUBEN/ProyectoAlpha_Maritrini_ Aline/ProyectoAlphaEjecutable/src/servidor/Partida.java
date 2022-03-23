/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author alineitzelbecerracarranza
 */

public class Partida {
    private static HashMap <String, Integer> puntuaciones;
    private final static int puntosGanar =5;
    private static int celdaMonstruo;
    private static String ganador;
    
    public Partida(){
        this.puntuaciones= new HashMap<String, Integer>();
        int celdaMonstruo=-1;
        this.ganador="";
    }
    
    public String getGanador(){
        return this.ganador;
    }
    
    synchronized public void setGanador(String usuario){
        if(ganador.equals(""))
            this.ganador=usuario;
    }
    
    public boolean registraUsuario(String usuario){
        boolean res =false;
        if(!existeUsuario(usuario)){
            puntuaciones.put(usuario, 0);
            res=true;
        }
        return res;
    }
    
    
    public int puntuacionUsuario(String usuario){ 
        return puntuaciones.containsKey(usuario) ? puntuaciones.get(usuario):0;
    }
    
    public void agregaPunto(String usuario){
        int num = puntuaciones.containsKey(usuario)? puntuaciones.get(usuario):0; 
        puntuaciones.put(usuario, num+1);
        if((num+1)==puntosGanar)
            setGanador(usuario);
    }
    
    public void reiniciar(){
        this.puntuaciones= new HashMap<String, Integer>();
        this.celdaMonstruo=-1;
        this.ganador="";
    } 
    
    public void puntuacionesTodos(){
        
        System.out.println("---------------------------");
        System.out.println("Usuario \t Puntuacion");
        System.out.println("---------------------------");
        for (String usuario : puntuaciones.keySet())
            System.out.println(""+usuario+" \t "+puntuaciones.get(usuario));
        
    }

    public int getCeldaMonstruo() {
        return celdaMonstruo;
    }

    public void setCeldaMonstruo(int celdaMonstruo) {
        Partida.celdaMonstruo = celdaMonstruo;
    }
    
    public boolean existeUsuario(String usuario){
        return puntuaciones.containsKey(usuario);
    }
    
}
