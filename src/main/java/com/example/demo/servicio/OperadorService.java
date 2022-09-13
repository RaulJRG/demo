package com.example.demo.servicio;

import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.Operador;

import java.util.List;
import java.util.Map;

public interface OperadorService {

    public List<Operador> buscar(String filtro, String busqueda);

    //public Operador findOperador(Long id);

    public List<Incidencia> findIncidenciasByIdOperador(Long id);

    public void eliminar(Long id);

    public void crear(Operador operador);

    public void guardar(Long id, Map<Object,Object> fields);

    public void addIncidencia(Long id, Incidencia incidencia);
}
