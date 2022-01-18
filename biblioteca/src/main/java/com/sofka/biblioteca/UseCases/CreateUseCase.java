package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateUseCase implements SaveRecurso {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public CreateUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        return recursoRepository
                .save(recursoMapper.mapDTOToEntity(null).apply(recursoDTO))
                .map(Recurso::getNombre);
    }
}
