package com.sofka.biblioteca.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private String tipoRecurso;
    private String areaTematica;
    private Boolean enPrestamo = false;
    private String fechaPrestamo;
    private String[] tiposRecurso = {"Libro", "Revista", "Material Audiovisual"};
    private String[] areasTematicas = {"Literatura", "Ciencia", "Arte", "Cine", "Historia"};

    public Recurso(){

    }

    public Recurso(String id, String nombre, String tipoRecurso, String areaTematica) {
        this.id = id;
        this.nombre = nombre;
        this.tipoRecurso = tipoRecurso;
        this.areaTematica = areaTematica;
        this.enPrestamo = false;
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
}
