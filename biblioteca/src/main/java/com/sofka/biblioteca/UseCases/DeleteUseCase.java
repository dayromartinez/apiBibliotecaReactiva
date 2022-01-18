package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteUseCase implements Function<String, Mono<Void>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public DeleteUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El ID es requerido para esta acción");
        return recursoRepository
                .deleteById(id);
    }
}
