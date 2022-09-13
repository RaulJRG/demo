package com.example.demo.servicio;

import com.example.demo.modelo.Incidencia;

import java.util.List;
import java.util.Map;

public interface IncidenciaService {

    public Long getIdOperador(Long id);

    public List<Incidencia> buscar(String filtro, String busqueda);

    public void eliminar(Long id);

    public void crear(Incidencia incidencia, Long operadorId);

    public void cerrar(Long id, Map<Object,Object> fields);

}
