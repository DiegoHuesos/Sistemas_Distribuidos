package juegopapa;

public class Juego {
    public static void main(String[] args){
        Jugador jug1=new Jugador("Jugador 1","Jugador 2");
        Jugador jug2=new Jugador("Jugador 2","Jugador 1");

        jug1.start();
        jug2.start();
    }
}
