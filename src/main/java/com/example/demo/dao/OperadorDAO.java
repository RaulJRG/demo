package com.example.demo.dao;

import com.example.demo.modelo.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperadorDAO extends JpaRepository<Operador, Long> {

    public List<Operador> findAll();

    public Operador findOperadorById(Long id);

    public List<Operador> findByNombre(String nombre);

    public List<Operador> findByApellidos(String apellidos);

}
