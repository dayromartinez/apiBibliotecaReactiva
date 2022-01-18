package com.sofka.biblioteca.UseCases;


import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DevolverRecursoUseCase implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public DevolverRecursoUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(String id) {

        Objects.requireNonNull(id, "El ID es requerido para esta acción");

        return recursoRepository.findById(id)
                .map(recursoMapper.mapEntityToDTO())
                .flatMap(recursoDTO -> !recursoDTO.getEnPrestamo() ? Mono.just("El recurso consultado no se encuentra en préstamo. Inténtelo nuevamente") : (
                        recursoRepository.save(recursoMapper.mapDTOToEntity(recursoDTO.getId())
                                        .apply(new RecursoDTO(recursoDTO.getId(), recursoDTO.getNombre(), recursoDTO.getTipoRecurso(),
                                                recursoDTO.getAreaTematica(), false, "")))
                                .flatMap(recurso -> Mono.just("Recurso devuelto satisfactoriamente a la biblioteca")))
                );
    }
}
