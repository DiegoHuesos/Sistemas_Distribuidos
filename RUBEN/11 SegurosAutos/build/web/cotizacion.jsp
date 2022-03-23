<%-- 
    Document   : cotizacion
    Created on : 15/02/2019, 05:55:17 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cotizacion</title>
    </head>
    <body>
        <h1>Cotización de seguro para Automóvil</h1>
        <%
            String nombre="";
            String edad="";
            String estado="";
            String genero="";
            String marca="";
            String modelo="";
            String placas="";
            
            //Obtenermos el nombre
            if (request.getParameter("nombreCompleto")!=null){
                nombre=request.getParameter("nombreCompleto");
            }
            //Obtenemos el género
            if (request.getParameter("genero")!=null)
                genero=request.getParameter("genero");
            
            //Obtenemos el edad
            if (request.getParameter("edad")!=null)
                edad=request.getParameter("edad");
            
            //Obtenemos el estado
            if (request.getParameter("estado")!=null)
                estado=request.getParameter("estado");
            
            //Obtenemos el marca
            if (request.getParameter("marca")!=null)
                marca=request.getParameter("marca");
            
            //Obtenemos el modelo
            if (request.getParameter("modelo")!=null)
                modelo=request.getParameter("modelo");
            
            //Obtenemos el placas
            if (request.getParameter("placas")!=null)
                placas=request.getParameter("placas");
                 
            
            //Imprimimos los valores
            out.print("Apreciable "+nombre);
            out.print("<br>");
            out.print("En función a lo datos proporcionados <br>");
            out.print("Género: "+genero);
            out.print("<br>");
            out.print("Edad: "+edad);
            out.print("<br>");
            out.print("Estado: "+estado);
            out.print("<br>");
            out.print("Marca: "+marca);
            out.print("<br>");
            out.print("Modelo: "+modelo);
            out.print("<br>");
            out.print("Placas: "+placas);
            out.print("<br>");
            
            out.print("<b>La cotización de su seguro es:</b><br>");
            
            if(modelo.compareTo("2000")>0)
                out.print("<h1>$7,800.00 pesos</h1><br>");
            else
                out.print("<h1>$4,000.00 pesos</h1><br>");
            
        %>
        
    </body>
</html>
