package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@SpringBootTest
public class UpdateUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;
    @SpyBean
    private UpdateUseCase updateUseCase;

    @Test
    void updateRecurso(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");

        var recursoDTO = new RecursoDTO();
        recursoDTO.setId("1111");
        recursoDTO.setNombre("Cien años de soledad");
        recursoDTO.setTipoRecurso("Libro");
        recursoDTO.setAreaTematica("Literatura");
        recursoDTO.setEnPrestamo(true);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);
        recursoDTO.setFechaPrestamo(text);

        Mockito.when(recursoRepository.save(Mockito.any(Recurso.class))).thenReturn(Mono.just(recurso));
        var response = updateUseCase.apply(recursoDTO);

        Assertions.assertEquals(Objects.requireNonNull(response.block()), recurso.getNombre());
        Mockito.verify(recursoRepository).save(Mockito.any());
    }
}
