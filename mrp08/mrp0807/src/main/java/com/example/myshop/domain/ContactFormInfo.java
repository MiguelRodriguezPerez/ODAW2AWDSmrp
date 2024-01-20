package com.example.myshop.domain;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ContactFormInfo {
    @NotEmpty
    private String nombre;
    @Email
    private String email;
    private TipoContacto tipoContacto;
    private String comentarios;
    @AssertTrue
    private boolean aceptaCondiciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isAceptaCondiciones() {
        return aceptaCondiciones;
    }

    public void setAceptaCondiciones(boolean aceptaCondiciones) {
        this.aceptaCondiciones = aceptaCondiciones;
    }

}
