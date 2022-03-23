function cambiaGenero(element) {
    s = document.getElementById("genero_texto");
    if (element.value == "Masculino") {
        s.innerHTML = "Sr.";
    } else {
        s.innerHTML = "Sra.";
    }
}

function cambiaNombre() {
    s = document.getElementById("nombre_texto");
    nombre = document.getElementById("Nombre").value;
    apellido = document.getElementById("Apellido").value;
    s.innerHTML = nombre + " " + apellido;
}