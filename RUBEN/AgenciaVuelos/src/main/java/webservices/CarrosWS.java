/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import clasesExtra.Reservacion;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ruben
 */
@WebService(serviceName = "CarrosWS")
public class CarrosWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "creaReservacionCarro")
    public Reservacion creaReservacionCarro(@WebParam(name = "ciudad") String ciudad, @WebParam(name = "fecha_inicial") String fecha_inicial, @WebParam(name = "fecha_final") String fecha_final) {
        //TODO write your implementation code here:
        Reservacion res=new Reservacion('C'+ciudad+'_'+fecha_inicial,100);
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "confirmaReservacionCarro")
    public boolean confirmaReservacionCarro(@WebParam(name = "tarjeta_credito") String tarjeta_credito, @WebParam(name = "id_reservacion") String id_reservacion) {
        //TODO write your implementation code here:
        return tarjeta_credito.contains("F");
    }
}
