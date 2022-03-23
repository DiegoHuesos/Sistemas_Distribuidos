/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import clasesExtra.Reservacion;
import webservices.*;
/**
 *
 * @author ruben
 */
@WebService(serviceName = "AgenciaWS")
public class AgenciaWS {
    
    HotelWS hotel=new HotelWS();
    VuelosWS vuelos=new VuelosWS();
    CarrosWS carros=new CarrosWS();
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "creaReservacion")
    public Reservacion creaReservacion(@WebParam(name = "origen") String origen, @WebParam(name = "destino") String destino, @WebParam(name = "fecha_inicial") String fecha_inicial, @WebParam(name = "fecha_final") String fecha_final) {
        //TODO write your implementation code here:
        
        Reservacion reservaHotel=hotel.creaReservacionHotel(destino, fecha_inicial, fecha_final);
        Reservacion reservaVuelo=vuelos.creaReservacionVuelo(origen,destino,fecha_inicial,fecha_final);
        Reservacion reservaCarro=carros.creaReservacionCarro(destino, fecha_inicial, fecha_final);
       
        Reservacion res=new Reservacion(reservaHotel.getId()+'-'+reservaVuelo.getId()+'-'+reservaCarro.getId(),reservaHotel.getCost()+reservaVuelo.getCost()+reservaCarro.getCost());
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "confirmaReservacion")
    public boolean confirmaReservacion(@WebParam(name = "tarjeta_credito") String tarjeta_credito, @WebParam(name = "id_reservacion") String id_reservacion) {
        //TODO write your implementation code here:
        String[] id=id_reservacion.split("-");
        return (hotel.confirmaReservacionHotel(tarjeta_credito, id[0]) && vuelos.confirmaReservacionVuelo(tarjeta_credito, id[1]) && carros.confirmaReservacionCarro(tarjeta_credito, id[2]));
    }

    
}
