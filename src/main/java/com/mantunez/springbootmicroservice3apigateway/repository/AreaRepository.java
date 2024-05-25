package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Area;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query(value = "select * from area where nombre= ?1", nativeQuery = true)
    public List<Area> findByNombre(String nombre);

    @Query(value = "select * from area where estado = true", nativeQuery = true)
    public List<Area> getAllArea();

    @Query(value = "select * from area where id_grado=?2 and nombre = ?1 and estado = 1 and eliminado=0", nativeQuery = true)
    public Area getAreaGrado(String nombre, Long idGrado);

    @Query(value = "SELECT * FROM area WHERE eliminado = 0 " +
            "AND ((:nombre IS NULL OR nombre LIKE CONCAT('%', :nombre, '%')) " +
            "AND (:estado IS NULL OR estado = :estado) " +
            "AND (:idGrado IS NULL OR id_grado = :idGrado))",
            nativeQuery = true)
    public List<Area> getAreaGradoActiva(@Param("nombre") String nombre,
                                         @Param("estado") Boolean estado,
                                         @Param("idGrado") Long idGrado);
}
