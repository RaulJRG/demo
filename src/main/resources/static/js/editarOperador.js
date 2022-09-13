var id = null;

$(document).ready(function () {
    obtenerId();
});

async function obtenerId(){
    id = /[&?]id=([^&]+)/.exec(location.search);
    id = id ? id[1].replace(/"/g, '&quot;') : null;
    document.querySelector('#tituloForm').outerHTML = '<h2 class="text-center" id="tituloForm">Actualizar usuario '+id+'</h2>';
}

async function guardarOperador() {

    let operador = {};
    operador.nombre = document.getElementById('nombre').value;
    operador.apellidos = document.getElementById('apellidos').value;

    $.ajax({
       type: 'PATCH',
       url: 'api/operadores/'+id+'',
       data: JSON.stringify(operador),
       processData: false,
       contentType: 'application/merge-patch+json', 
    });
    window.location.replace("/verOperadores.html");





}