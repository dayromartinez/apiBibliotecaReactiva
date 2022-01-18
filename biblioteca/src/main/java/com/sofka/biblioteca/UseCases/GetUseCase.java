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
public class GetUseCase implements Function<String, Mono<RecursoDTO>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public GetUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDTO> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido para buscar el recurso");
        return recursoRepository.findById(id)
                .map(recursoMapper.mapEntityToDTO())
                .flatMap(mapAgregadoRecurso());
    }

    private Function<RecursoDTO, Mono<RecursoDTO>> mapAgregadoRecurso() {
        return Mono::just;
    }
}
