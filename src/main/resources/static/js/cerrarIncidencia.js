var id = null;

$(document).ready(function () {
    obtenerId();
});

async function obtenerId(){
    id = /[&?]id=([^&]+)/.exec(location.search);
    id = id ? id[1].replace(/"/g, '&quot;') : null;
    document.querySelector('#tituloForm').outerHTML = '<h2 class="text-center" id="tituloForm">Cerrar Incidencia '+id+'</h2>';
}

async function cerrarIncidencia() {

    let incidencia = {};
    incidencia.solucion = document.getElementById('solucion').value;
    incidencia.estado = 'Cerrada';
    let hoy2 = new Date();
    incidencia.fechaCierre = hoy2.toLocaleDateString();

    try{
        $.ajax({
           type: 'PATCH',
           url: 'api/incidencias/'+id+'',
           data: JSON.stringify(incidencia),
           processData: false,
           contentType: 'application/merge-patch+json',
        });
        window.location.replace("/verIncidencias.html");
    }
    catch(error){
        console.log("Error cerrando incidencia");
    }





}