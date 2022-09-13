$(document).ready(function () {

});

async function crearOperador() {

    let operador = {};
    operador.nombre = document.getElementById('nombre').value;
    operador.apellidos = document.getElementById('apellidos').value;

    const response = await fetch('api/operadores', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(operador)
    });
    window.location.replace("/verOperadores.html");

}