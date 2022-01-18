package com.sofka.biblioteca.UseCases;


import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DisponibilidadRecursoUseCase implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public DisponibilidadRecursoUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(String nombre) {
        Objects.requireNonNull(nombre, "El nombre es requerido para buscar el recurso");
        return recursoRepository.findByNombre(nombre)
                .map(recursoMapper.mapEntityToDTO())
                .flatMap(recursoDTO -> recursoDTO.getEnPrestamo() ? Mono.just("El recurso consultado se encuentra en préstamo") :
                        Mono.just("El recurso consultado se encuentra disponible para su préstamo"));
    }
}
