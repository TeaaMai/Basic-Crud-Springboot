package com.larson.technology.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Column(name = "APELLIDO_MATERNO")
    @NotNull(message = "El apellido materno no puede ser nulo")
    private String apellidoMaterno;

    @Column(name = "APELLIDO_PATERNO")
    @NotNull(message = "El apellido paterno no puede ser nulo")
    private String apellidoPaterno;

    @Column(name = "EMAIL", unique = true)
    @NotNull(message = "El email no puede ser nulo")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
