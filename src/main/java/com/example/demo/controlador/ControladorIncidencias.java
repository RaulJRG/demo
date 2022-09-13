package com.example.demo.controlador;

import com.example.demo.dao.IncidenciaDAO;
import com.example.demo.dao.OperadorDAO;
import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.Operador;
import com.example.demo.servicio.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/api/incidencias")
public class ControladorIncidencias {
    @Autowired
    private IncidenciaService incidenciaService;

    //Obtiene el id del operador con el id de la incidenicia
    @GetMapping("/operador/{id}")
    public Long getOperadorId(@PathVariable Long id){
        return incidenciaService.getIdOperador(id);
    }

    //Obtiene las incidencias a partir de un filtro de búsqueda
    @GetMapping("/{filtro}")
    public List<Incidencia> buscarIncidencias(@PathVariable() String filtro, @RequestParam(name = "busqueda") String busqueda){
        return incidenciaService.buscar(filtro, busqueda);
    }

    //Elimina una incidencia con su id
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        incidenciaService.eliminar(id);
    }

    //Crea una incidencia adicionando el id del operador
    @PostMapping()
    public void crear(@RequestBody() Incidencia incidencia, @RequestParam("operadorId") Long operadorId){
        incidenciaService.crear(incidencia,operadorId);
    }

    //Cierra una incidencia, actualizando parcialmente sus campos
    @PatchMapping("/{id}")
    public void cerrar(@PathVariable(name = "id") Long id, @RequestBody Map<Object,Object> fields){
        incidenciaService.cerrar(id,fields);
    }

}
