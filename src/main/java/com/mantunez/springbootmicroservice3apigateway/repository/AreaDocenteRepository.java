package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Alumno;
import com.mantunez.springbootmicroservice3apigateway.model.AreaDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AreaDocenteRepository extends JpaRepository<AreaDocente, Long> {

    @Query(value = "SELECT * FROM area_docente WHERE id_area= ?1 and id_docente=?2 and estado=true", nativeQuery = true)
    public Optional<AreaDocente> verificarExiste(Long id_area, Long id_docente);

    @Query(value = "SELECT * FROM area_docente WHERE estado=true", nativeQuery = true)
    public List<AreaDocente> getActivos();
}
