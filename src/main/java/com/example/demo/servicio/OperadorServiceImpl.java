package com.example.demo.servicio;

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
public class OperadorServiceImpl implements OperadorService {

    @Autowired
    private OperadorDAO operadorDAO;

    @Override
    public List<Operador> buscar(String filtro, String busqueda) {
        //Crea la lista de operadores
        List<Operador> operadores = new ArrayList<>();
        //Evalua el filtro elegido
        if(filtro.equalsIgnoreCase("id") && !busqueda.isEmpty()){
            //Si se filtra por id busca a los operadores con ese id
            Operador operador = operadorDAO.findOperadorById(Long.parseLong(busqueda));
            if(operador != null){
                operadores.add(operador);
            }
        }
        else if (filtro.equalsIgnoreCase("nombre")){
            //Si se filtra por día, recupera a los operador por nombre
            operadores = operadorDAO.findByNombre(busqueda);
        }
        else if (filtro.equalsIgnoreCase("apellidos")){
            //Si se filtra por operador se busca por el id del operador
            operadores = operadorDAO.findByApellidos(busqueda);
        }
        else
            //Si no usó ningún filtro, busca a todos los operadores
            operadores = operadorDAO.findAll();
        return operadores;
    }

    @Override
    public List<Incidencia> findIncidenciasByIdOperador(Long id) {
        List<Incidencia> incidencias = new ArrayList<>();
        Operador operador = findOperador(id);
        if(operador != null)
            incidencias.addAll(operador.getIncidencias());
        return incidencias;
    }

    //@Override
    public Operador findOperador(Long id) {
        return operadorDAO.findOperadorById(id);
    }



    @Override
    public void eliminar(Long id) {
        operadorDAO.deleteById(id);
    }

    @Override
    public void crear(Operador operador) {
        operadorDAO.save(operador);
    }

    @Override
    public void guardar(Long id, Map<Object,Object> fields) {
        //Se recupera el operador con el id
        Operador operador = operadorDAO.findOperadorById(id);
        //Se usa reflection para actualizar los campos recibidos
        fields.forEach((k,v) ->{
            Field field = ReflectionUtils.findField(Operador.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, operador, v);
        });
        //Se guardan los valores en la base de datos
        operadorDAO.save(operador);
    }

    @Override
    public void addIncidencia(Long id, Incidencia incidencia) {
        Operador operador = operadorDAO.findOperadorById(id);
        if(operador != null) {
            operador.getIncidencias().add(incidencia);
            operadorDAO.save(operador);
        }
    }

}
