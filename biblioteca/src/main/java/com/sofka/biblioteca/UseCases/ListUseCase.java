package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListUseCase implements Supplier<Flux<RecursoDTO>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public ListUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return recursoRepository.findAll()
                .map(recursoMapper.mapEntityToDTO());
    }
}
