const parametros = new URLSearchParams(window.location.search);

const nombreCompleto = parametros.get("NombreCompleto");
const genero = parametros.get("Genero");
const edad = parametros.get("Edad");
const estado = parametros.get("Estado");
const marca = parametros.get("Marca");
const modelo = parametros.get("Modelo");
const placas = parametros.get("Placas");

document.writeln("<b>Estimad" + nombreCompleto + "</b>");
document.writeln("<br>En función a los datos proporcionados:");
document.writeln("<br>Género: " + genero);
document.writeln("<br>Edad: " + edad);
document.writeln("<br>Estado: " + estado);
document.writeln("<br>Marca: " + marca);
document.writeln("<br>Modelo: " + modelo);
document.writeln("<br>Placas: " + placas);
document.writeln("<br><b>La cotización de su seguro es:</b>");

if (parseInt(modelo.toString) > 2000) {
    document.writeln("<br><h2>$7,800.00 pesos</h2>");
} else {
    document.writeln("<br><h2>$4,200.00 pesos</h2>");
}