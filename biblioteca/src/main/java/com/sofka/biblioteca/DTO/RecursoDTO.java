package com.sofka.biblioteca.DTO;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RecursoDTO {


    private String id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String tipoRecurso;
    @NotBlank
    private String areaTematica;
    private Boolean enPrestamo = false;
    private String fechaPrestamo;

    public RecursoDTO() {
    }

    public RecursoDTO(String nombre, String tipoRecurso, String areaTematica) {
        this.nombre = nombre;
        this.tipoRecurso = tipoRecurso;
        this.areaTematica = areaTematica;
        this.enPrestamo = false;
        this.fechaPrestamo = "";
    }

    public RecursoDTO(String id, String nombre, String tipoRecurso, String areaTematica, Boolean enPrestamo, String fechaPrestamo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoRecurso = tipoRecurso;
        this.areaTematica = areaTematica;
        this.enPrestamo = enPrestamo;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Boolean getEnPrestamo() {
        return enPrestamo;
    }

    public void setEnPrestamo(Boolean enPrestamo) {
        this.enPrestamo = enPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(tipoRecurso, that.tipoRecurso) && Objects.equals(areaTematica, that.areaTematica) && Objects.equals(enPrestamo, that.enPrestamo) && Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipoRecurso, areaTematica, enPrestamo, fechaPrestamo);
    }

    @Override
    public String toString() {
        return "RecursoDTO{" +
                "id='" + id + '\'' +
                ", name='" + nombre + '\'' +
                ", tipoRecurso='" + tipoRecurso + '\'' +
                ", areaTematica='" + areaTematica + '\'' +
                ", enPrestamo=" + enPrestamo +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                '}';
    }
}
