package com.sofka.biblioteca.UseCases;


import com.sofka.biblioteca.DTO.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveRecurso {
    Mono<String> apply(@Valid RecursoDTO recursoDTO);
}
