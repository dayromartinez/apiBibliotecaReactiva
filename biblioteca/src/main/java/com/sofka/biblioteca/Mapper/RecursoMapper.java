package com.sofka.biblioteca.Mapper;

import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.DTO.RecursoDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component
public class RecursoMapper {

    public Function<RecursoDTO, Recurso> mapDTOToEntity(String id) {
        return updateRecurso -> {
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setTipoRecurso(updateRecurso.getTipoRecurso());
            recurso.setAreaTematica(updateRecurso.getAreaTematica());
            recurso.setEnPrestamo(updateRecurso.getEnPrestamo());
            recurso.setFechaPrestamo(updateRecurso.getFechaPrestamo());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapEntityToDTO() {
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getTipoRecurso(),
                entity.getAreaTematica(),
                entity.getEnPrestamo(),
                entity.getFechaPrestamo()
        );
    }
}
