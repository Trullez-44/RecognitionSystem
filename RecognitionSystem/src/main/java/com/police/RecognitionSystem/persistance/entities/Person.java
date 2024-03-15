package com.police.RecognitionSystem.persistance.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="personaId")
    private Long personaId;
    @Column(name = "nombrePersona")
    private String nombre;
    @Column(name = "apellidoPersona")
    private String apellido;
    @Column(name = "direccionPersona")
    private String direccion;
    @Column(name = "emailPersona")
    private String email;
    @Column(name = "cromosoma")
    private String cromosoma;

}
