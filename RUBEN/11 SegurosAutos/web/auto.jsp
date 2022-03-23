<%-- 
    Document   : auto
    Created on : 15/02/2019, 05:35:21 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Auto</title>
    </head>
    <body>
        <h1>Sistema de Cotización de Seguros de Auto</h1>
        <form action="cotizacion.jsp"> 
        <b>Datos personales:</b><br>
        <%
            String nombre="";
            String apellidos="";
            String edad="";
            String estado="";
            String genero="";
            
            //Obtenermos el nombre
            if (request.getParameter("nombre")!=null && request.getParameter("apellidos")!=null ){
                nombre=request.getParameter("nombre");
                apellidos=request.getParameter("apellidos");
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
                 
            
            //Imprimimos los valores
            out.print("Apreciable "+nombre+" "+apellidos);
            out.print("<input type='hidden' name='nombreCompleto' value='"+nombre+" "+apellidos+"' />");
            out.print("<br>");
            out.print("Género: "+genero);
            out.print("<input type='hidden' name='genero' value='"+genero+"' />");
            out.print("<br>");
            out.print("Edad: "+edad);
            out.print("<input type='hidden' name='edad' value='"+edad+"' />");
            out.print("<br>");
            out.print("Estado: "+estado);
            out.print("<input type='hidden' name='estado' value='"+estado+"' />");
            out.print("<br>");
        %>
        
        <b>Datos del auto</b>
        <table border="1">
            <thead>
                <tr>
                    <th>Campo</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Marca:</td>
                    <td><select name="marca">
                            <option>Chevrolet</option>
                            <option>Honda</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Modelo</td>
                    <td><input type="text" name="modelo" value="" /></td>
                </tr>
                <tr>
                    <td>Placas</td>
                    <td><input type="text" name="placas" value="" /></td>
                </tr>
                <tr>
                    <td><input type="reset" value="Limpiar" /></td>
                    <td><input type="submit" value="Enviar" /></td>
                </tr>
            </tbody>
        </table>
        </form> 
    </body>
</html>
