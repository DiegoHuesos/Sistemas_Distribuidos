<%-- 
    Document   : cotizacion
    Created on : 15/02/2019, 05:27:24 PM
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
        <h1>Cotizacion de seguro de autos</h1>        
        <form action="cotizacion.jsp">
            <h2>Datos personales</h2>
                        
            <h2>Estimado Sr(a). <%out.print(request.getParameter("Nombre"));%> <%out.print(request.getParameter("Apellido"));%> </h2>
            <h2>En funcion de los datos proporcionados</h2>
            <h2>Genero: <%out.print(request.getParameter("Genero"));%> </h2>
            <h2>Edad: <%out.print(request.getParameter("Edad"));%> </h2>            
            <h2>Estado: <%out.print(request.getParameter("Estado"));%> </h2>
            <h2>Marca <%out.print(request.getParameter("Marca"));%> </h2>
            <h2>Modelo <%out.print(request.getParameter("Modelo"));%> </h2>
            <h2>Placas <%out.print(request.getParameter("Placa"));%> </h2>
            <h2>La cotizacion de su seguro es:</h2>
            
            <%
                double numero;
                numero = Double.parseDouble(request.getParameter("Modelo"));
                numero = (numero-2000)*1000;
                out.print("$"+numero+" pesos");
                %>
            
    </body>
</html>
