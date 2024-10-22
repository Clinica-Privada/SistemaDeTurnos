function irAPagina() {
    var seleccion = document.getElementById("turnos").value;
    if (seleccion) {
        window.location.href = seleccion; 
    }
}