var filtro = '';
var busqueda = '';
$(document).ready(function () {
    //alert(123);
    buscarIncidencias();
});

/*async function cargarIncidencias() {
    const response = await fetch('api/incidencias', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const incidencias = await response.json();
 
    //fetch('http://localhost:8080/api/incidencias/1').then(response => response.json()).then(data => console.log(data));
    let filasHTML = '<tbody>'
    for (let incidencia of incidencias) {
        filasHTML +=
            '<tr>' +
                '<td>' + incidencia.id + '</td>'+
                '<td>' + incidencia.titulo + '</td>'+
                '<td>' + incidencia.descripcion + '</td>'+
                '<td>' + incidencia.estado + '</td>'+
                '<td>' + incidencia.fecha + '</td>'+
                '<td><a href = "#" onclick = "eliminar('+incidencia.id+')" >Eliminar</a></td>'+
                '<td><a href = "/cerrarIncidencia.html?id='+incidencia.id+'"  >Cerrar</a></td>'+
            '</tr>';
    }
    filasHTML += '</tbody>';
    document.querySelector('#incidencias tbody').outerHTML = filasHTML;
}*/

async function eliminar(id){
    if(confirm('¿Eliminar usuario '+id+'?')){
        const response = await fetch('api/incidencias/'+id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        location.reload();
    }
    //const incidencias = await response.json();
}

async function buscarIncidencias(){
    filtro = await document.querySelector('#filtroBusqueda').value;
    filtro = filtro == null ? '' : filtro;
    busqueda = await document.querySelector('#valorBusqueda').value;
    busqueda = busqueda == null ? '' : busqueda;
    console.log("filtro "+filtro+" busqueda "+busqueda);
    const response = await fetch('api/incidencias/'+filtro+'?busqueda='+busqueda+'', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const incidencias = await response.json();

    let filasHTML = '<tbody>';
    try{
        if(incidencias.length == 0)
            filasHTML += '<td colspan = 100% class="text-center">No se encontraron resultados</td>';
        else{
            for (let incidencia of incidencias) {
                let operadorId = '';
                try{
                    const request2 = await fetch('api/incidencias/operador/'+incidencia.id, {
                         method: 'GET',
                         headers: {
                             'Accept': 'application/json',
                             'Content-Type': 'application/json'
                         }
                     });
                     operadorId = await request2.json();
                }
                catch(error){
                    operadorId = '';
                }
                filasHTML +=
                    '<tr>' +
                        '<td>' + incidencia.id + '</td>'+
                        '<td>' + incidencia.titulo + '</td>'+
                        '<td>' + incidencia.descripcion + '</td>'+
                        '<td>' + incidencia.diagnostico + '</td>'+
                        '<td>' + incidencia.estado + '</td>'+
                        '<td>' + incidencia.fechaApertura + '</td>'+
                        '<td>' + operadorId + '</td>'+
                        '<td>' + incidencia.solucion + '</td>'+
                        '<td>' + incidencia.fechaCierre + '</td>'+
                        '<td><a href = "#" onclick = "eliminar('+incidencia.id+')" >Eliminar</a></td>'+
                        '<td><a href = "/cerrarIncidencia.html?id='+incidencia.id+'"  >Cerrar</a></td>'+
                    '</tr>';
            }
        }

    }
    catch(error){
        filasHTML += '<td colspan = 100% class="text-center">No se encontraron resultados</td>';
    }
    filasHTML += '</tbody>';
    document.querySelector('#incidencias tbody').outerHTML = filasHTML;

}