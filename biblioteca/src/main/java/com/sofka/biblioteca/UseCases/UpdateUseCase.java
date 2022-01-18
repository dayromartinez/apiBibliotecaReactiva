package com.sofka.biblioteca.UseCases;


import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateUseCase implements SaveRecurso {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public UpdateUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "El ID del recurso es requerido");
        return recursoRepository
                .save(recursoMapper.mapDTOToEntity(recursoDTO.getId()).apply(recursoDTO))
                .map(Recurso::getNombre);
    }
}
