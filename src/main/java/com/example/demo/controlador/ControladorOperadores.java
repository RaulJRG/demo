package com.example.demo.controlador;

import com.example.demo.dao.OperadorDAO;
import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.Operador;
import com.example.demo.servicio.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/operadores")
public class ControladorOperadores {

    @Autowired
    private OperadorService operadorService;

    //Obtiene los operadores a partir de un filtro de búsqueda
    @GetMapping("/{filtro}")
    public List<Operador> buscarOperador(@PathVariable() String filtro, @RequestParam(name = "busqueda") String busqueda){
        System.out.println("buscando operadores, filtro = "+filtro+", busqueda = "+busqueda);
        return operadorService.buscar(filtro, busqueda);
    }

    /*@GetMapping("/{id}")
    public Operador getOperador(@PathVariable Long id){
        return operadorService.findOperador(id);
    }*/

    //Elimina una incidencia con su id
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        operadorService.eliminar(id);
    }


    //Crea un nuevo operador
    @PostMapping()
    public void crear(@RequestBody() Operador operador){
        operadorService.crear(operador);
    }

    //Actualiza parcialmente los datos de un operador
    @PatchMapping("/{id}")
    public void actualizar(@PathVariable(name = "id") Long id, @RequestBody Map<Object,Object> fields){
        operadorService.guardar(id,fields);
    }

}
