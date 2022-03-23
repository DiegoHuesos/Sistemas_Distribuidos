<%-- 
    Document   : index
    Created on : 15/02/2019, 05:27:03 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de cotizacion de seguro de autos</title>
    </head>
    <body>
        <h1>Sistema de cotizacion de seguro de autos</h1>        
        <form action="auto.jsp">
                                   
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="Nombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apellido </td>
                        <td><input type="text" name="Apellido" value="" /></td>
                    </tr>
                    <tr>
                        <td>Genero</td>
                        <td>
                            <input type="radio" name="Genero" value="Hombre" />Hombre
                            <input type="radio" name="Genero" value="Mujer" />Mujer
                        </td>
                    </tr>
                    <tr>
                        <td>Edad</td>
                        <td><input type="text" name="Edad" value="" /></td>
                    </tr>
                    <tr>
                        <td>Estado</td>
                        <td><select name="Estado">
                                <option>Jalisco</option>
                                <option>CDMX</option>
                                <option>Nuevo Leon</option>
                                <option>Yucatan</option>
                            </select></td>
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
