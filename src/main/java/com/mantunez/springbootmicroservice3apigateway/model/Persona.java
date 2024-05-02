package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "numero_Documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDateTime fechaNacimiento;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "id")
    private Tipo_Documento tipoDocumento;

    @Column(name = "sexo", nullable = false)
    private String sexo;
}
