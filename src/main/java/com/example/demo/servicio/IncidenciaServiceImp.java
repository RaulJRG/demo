package com.example.demo.servicio;

import com.example.demo.dao.IncidenciaDAO;
import com.example.demo.dao.OperadorDAO;
import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IncidenciaServiceImp implements IncidenciaService{

    @Autowired
    private IncidenciaDAO incidenciaDAO;
    @Autowired
    private OperadorService operadorService;

    @Override
    public Long getIdOperador(Long id) {
        return incidenciaDAO.findOperadorIdById(id);
    }

    @Override
    public List<Incidencia> buscar(String filtro, String busqueda) {
        //Crea la lista de incidencias
        List<Incidencia> incidencias = new ArrayList<>();
        //Evalua el filtro elegido
        if(filtro.equalsIgnoreCase("id") && !busqueda.isEmpty()){
            //Si se filtra por id busca las incidencias con ese id
            try {
                Incidencia incidencia = incidenciaDAO.findIncidenciaById(Long.parseLong(busqueda));
                incidencias.add(incidencia);
            }
            catch(Exception e){

            }
        }
        else if (filtro.equalsIgnoreCase("dia")){
            //Si se filtra por día, recupera las incidencias por fecha de apertura
            incidencias = incidenciaDAO.findByFechaApertura(busqueda);
        }
        else if (filtro.equalsIgnoreCase("operador")){
            try {
                //Si se filtra por operador se buscan incidencias por el id del operador
                incidencias.addAll(operadorService.findIncidenciasByIdOperador(Long.parseLong(busqueda)));
            }
            catch(Exception e){

            }
        }
        else
            //Si no usó ningún filtro, busca todas las incidencias
            incidencias = incidenciaDAO.findAll();
        return incidencias;
    }

    @Override
    public void eliminar(Long id) {
        incidenciaDAO.deleteById(id);
    }

    @Override
    public void crear(Incidencia incidencia, Long operadorId) {
        incidenciaDAO.save(incidencia);
        operadorService.addIncidencia(operadorId, incidencia);
    }

    @Override
    public void cerrar(Long id, Map<Object, Object> fields) {
        //Se recupera la incidencia con el id
        Incidencia incidencia = incidenciaDAO.findIncidenciaById(id);
        //Se usa reflection para actualizar los campos recibidos
        fields.forEach((k,v) ->{
            Field field = ReflectionUtils.findField(Incidencia.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, incidencia, v);
        });
        //Se guardan los valores en la base de datos
        incidenciaDAO.save(incidencia);
    }
}
