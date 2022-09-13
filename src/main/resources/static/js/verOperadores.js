var filtro = '';
var busqueda = '';
$(document).ready(function () {
    //alert(123);
    buscarOperadores();
});

async function eliminar(id){
    if(confirm('¿Eliminar operador '+id+'?')){
        const response = await fetch('api/operadores/'+id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
        location.reload();
    }
}

async function buscarOperadores(){
    filtro = await document.querySelector('#filtroOperador').value;
    filtro = filtro == null ? '' : filtro;
    busqueda = await document.querySelector('#valorOperador').value;
    busqueda = busqueda == null ? '' : busqueda;
    console.log("Filtro '"+filtro+"' Busqueda '"+busqueda+"'");
    const response = await fetch('api/operadores/'+filtro+'?busqueda='+busqueda+'', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const operadores = await response.json();

    let filasHTML = '<tbody>';
    try{
        if(operadores.length == 0)
            filasHTML += '<td colspan = 100% class="text-center">No se encontraron resultados</td>';
        else{
            for (let operador of operadores) {
                filasHTML +=
                    '<tr>' +
                        '<td>' + operador.id + '</td>'+
                        '<td>' + operador.nombre + '</td>'+
                        '<td>' + operador.apellidos + '</td>'+
                        '<td><a href = "#" onclick = "eliminar('+operador.id+')" >Eliminar</a></td>'+
                        '<td><a href = "/editarOperador.html?id='+operador.id+'"  >Editar</a></td>'+
                    '</tr>';
            }
        }
    }
    catch(error){
        filasHTML += '<td colspan = 100% class="text-center">No se encontraron resultados</td>';
    }
    filasHTML += '</tbody>';
    document.querySelector('#operadores tbody').outerHTML = filasHTML;

}