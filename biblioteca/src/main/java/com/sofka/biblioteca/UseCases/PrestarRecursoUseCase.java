package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Collection.Recurso;
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
public class PrestarRecursoUseCase implements Function<String, Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public PrestarRecursoUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(String id) {

        Objects.requireNonNull(id, "El ID es requerido para esta acción");
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);

        return recursoRepository.findById(id)
                .map(recursoMapper.mapEntityToDTO())
                .flatMap(recursoDTO -> recursoDTO.getEnPrestamo() ? Mono.just("El recurso consultado ya se encuentra en préstamo") : (
                            recursoRepository.save(recursoMapper.mapDTOToEntity(recursoDTO.getId())
                                            .apply(new RecursoDTO(recursoDTO.getId(), recursoDTO.getNombre(), recursoDTO.getTipoRecurso(),
                                                    recursoDTO.getAreaTematica(), true, text)))
                                    .flatMap(recurso -> Mono.just("Recurso prestado con éxito")))
                );
    }
}
