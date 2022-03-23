
const parametros = new URLSearchParams(window.location.search);

const nombre = parametros.get("Nombre");
const apellido = parametros.get("Apellido");
const genero = parametros.get("Genero");
const edad = parametros.get("Edad");
const estado = parametros.get("Estado");

if (genero) {
    if (genero.localeCompare("Femenino") === 0) {
        document.writeln("<p>Estimada Sra. " + nombre + " " + apellido + "</p>");
        document.writeln("<input type='hidden' name='NombreCompleto' value='" + "a Sra. " + nombre + " " + apellido + "' />");
        document.writeln("<p>Género: " + genero + "</p>");
        document.writeln("<input type='hidden' name='Genero' value='" + genero + "' />");
    } else {
        document.writeln("<p>Estimado Sr. " + nombre + " " + apellido + "</p>");
        document.writeln("<input type='hidden' name='NombreCompleto' value='" + "o Sr. " + nombre + " " + apellido + "' />");
        document.writeln("<p>Género: " + genero + "</p>");
        document.writeln("<input type='hidden' name='Genero' value='" + genero + "' />");
    }
} else {
    document.writeln("<p>Estimad@ " + nombre + " " + apellido + "</p>");
    document.writeln("<input type='hidden' name='NombreCompleto' value='" + "@ " + nombre + " " + apellido + "' />");
    document.writeln("<p>Género: Desconocido</p>");
    document.writeln("<input type='hidden' name='Genero' value='Desconocido' />");
}

if (!edad) {
    document.writeln("<p>Edad: Desconocida</p>");
    document.writeln("<input type='hidden' name='Edad' value='Desconocida' />");
} else {
    document.writeln("<p>Edad: " + edad + "</p>");
    document.writeln("<input type='hidden' name='Edad' value='" + edad + "' />");
}

document.writeln("<p>Estado: " + estado + "</p>");
document.writeln("<input type='hidden' name='Estado' value='" + estado + "' />");
