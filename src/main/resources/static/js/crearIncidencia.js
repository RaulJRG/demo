$(document).ready(function () {

});

async function crearIncidencia() {

    let incidencia = {};
    incidencia.titulo = document.getElementById('titulo').value;
    incidencia.descripcion = document.getElementById('descripcion').value;
    incidencia.diagnostico = document.getElementById('diagnostico').value;
    incidencia.estado = 'Abierta';
    let today = new Date();
    incidencia.fechaApertura = today.toLocaleDateString();
    incidencia.fechaCierre = '';
    incidencia.solucion = '';

    let operadorId = document.getElementById('operador').value;

    try{
        const response = await fetch('api/incidencias?operadorId='+operadorId, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(incidencia)
        });
        window.location.replace("/verIncidencias.html");

    }
    catch(error){
        alert("Error creando la incidencia "+error.message());
    }

}