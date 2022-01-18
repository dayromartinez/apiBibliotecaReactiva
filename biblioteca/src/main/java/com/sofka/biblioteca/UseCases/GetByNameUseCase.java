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
public class GetByNameUseCase implements Function<String, Mono<RecursoDTO>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public GetByNameUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDTO> apply(String nombre) {
        Objects.requireNonNull(nombre, "El nombre es requerido para buscar el recurso");
        return recursoRepository.findByNombre(nombre)
                .map(recursoMapper.mapEntityToDTO())
                .flatMap(mapAgregadoRecursoNombre());
    }

    private Function<RecursoDTO, Mono<RecursoDTO>> mapAgregadoRecursoNombre() {
        return Mono::just;
    }
}
