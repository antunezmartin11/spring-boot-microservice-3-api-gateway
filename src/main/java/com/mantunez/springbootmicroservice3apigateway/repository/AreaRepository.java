package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Area;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query(value = "select * from area where nombre= ?1", nativeQuery = true)
    public Area findByNombre(String nombre);

    @Query(value = "select * from area where estado = true", nativeQuery = true)
    public List<Area> getAllArea();

    @Query(value = "select * from area where id_grado=?1 and nombre = ?2 and estado = true", nativeQuery = true)
    public Area getAreaGrado(String nombre, Long idGrado);
}
