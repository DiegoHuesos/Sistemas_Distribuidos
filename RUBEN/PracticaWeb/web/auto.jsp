<%-- 
    Document   : auto
    Created on : 15/02/2019, 05:27:15 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Auto</title>
        <script language="javascript" type="text/javascript">
            function detect(){
                if(navigator.cookieEnabled){
                    alert('Your browser has cookie enabled');
                } else {
                    alert('Your browser has cookie disabled');
                }
            }
        </script>
    </head>
    <body onload="detect();">
        
        <h1>Sistema de cotizacion de seguro de autos</h1>        
        <form action="cotizacion.jsp">
            <h2>Datos personales</h2>
            <input type="hidden" name="Apellido" value=<%out.print(request.getParameter("Apellido"));%>>
            <input type="hidden" name="Nombre" value=<%out.print(request.getParameter("Nombre"));%>>
            <input type="hidden" name="Genero" value=<%out.print(request.getParameter("Genero"));%>>
            <input type="hidden" name="Edad" value=<%out.print(request.getParameter("Edad"));%>>
            <input type="hidden" name="Estado" value=<%out.print(request.getParameter("Estado"));%>>
            
            <h2>Estimado Sr. <%out.print(request.getParameter("Nombre"));%> <%out.print(request.getParameter("Apellido"));%> </h2>
            <h2>Genero: <%out.print(request.getParameter("Genero"));%> </h2>
            <h2>Edad: <%out.print(request.getParameter("Edad"));%> </h2>            
            <h2>Estado: <%out.print(request.getParameter("Estado"));%> </h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Marca</td>
                        <td><select name="Marca">
                                <option>Ford</option>
                                <option>Honda</option>
                                <option>BMW</option>
                                <option>Chevrolet</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Modelo </td>
                        <td><input type="text" name="Modelo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Placas </td>
                        <td><input type="text" name="Placa" value="" /></td>
                    </tr>
                                                            
                   <tr>
                        <td><input type="reset" value="Limpiar" name="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" name="Enviar" /></td>
                    </tr>
                    
                </tbody>
            </table>


        </form>
    </body>
</html>
