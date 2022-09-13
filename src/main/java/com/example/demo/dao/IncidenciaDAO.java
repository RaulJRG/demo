package com.example.demo.dao;

import com.example.demo.modelo.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncidenciaDAO extends JpaRepository<Incidencia, Long> {

    public List<Incidencia> findAll();

    public Incidencia findIncidenciaById(Long id);

    public List<Incidencia> findByFechaApertura(String fechaApertura);

    //public List<Incidencia> findByOperadorId(Long operadorId);

    @Query(value = "SELECT i.operador_id FROM Incidencia i WHERE i.id = :id", nativeQuery = true)
    public Long findOperadorIdById(@Param("id") Long id);

    //@Query(value = "SELECT * FROM Incidencia i WHERE i.operador_id = :operadorId", nativeQuery = true)
    //public List<Incidencia> findAllByOperadorId(@Param("operadorId") Long operadorId);

    @Modifying
    @Query(value = "UPDATE Incidencia i SET i.operador_id = :operadorId WHERE i.id = :id", nativeQuery = true)
    void saveOperadorId(@Param("id") Long id, @Param("operadorId") Long operadorId);
}
