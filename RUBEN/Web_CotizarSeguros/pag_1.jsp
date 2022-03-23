<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sistema de cotizacion de seguro de autos</title>
    </head>
    <body>
        <h1>Sistema de Cotización de Seguros de Auto</h1>
        Datos personales
        <form action="pag_2.jsp"> 
        <table border="1">
            <thead>
                <tr>
                    <th>Campo</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="" /></td>
                </tr>
                <tr>
                    <td>Apellidos:</td>
                    <td><input type="text" name="apellidos" value="" /></td>
                </tr>
                <tr>
                    <td>Género:</td>
                    <td><input type="radio" name="genero" value="Masculino" />Masculino
                        <input type="radio" name="genero" value="Femenino" />Femenino
                    </td>
                </tr>
                <tr>
                    <td>Edad: </td>
                    <td><input type="text" name="edad" value="" /></td>
                </tr>
                <tr>
                    <td>Estado:</td>
                    <td><select name="estado">
                            <option>CDMX</option>
                            <option>Nuevo León</option>
                            <option>Jalisco</option>
                        </select></td>
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