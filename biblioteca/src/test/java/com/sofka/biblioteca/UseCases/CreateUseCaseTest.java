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

import java.util.Objects;

@SpringBootTest
public class CreateUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;
    @SpyBean
    private CreateUseCase createUseCase;

    @Test
    void createRecurso(){

        var recursoDto = new RecursoDTO();
        recursoDto.setNombre("Cien años de soledad");
        recursoDto.setTipoRecurso("Libro");
        recursoDto.setAreaTematica("Literatura");
        recursoDto.setEnPrestamo(false);
        recursoDto.setFechaPrestamo("");

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");

        Mockito.when(recursoRepository.save(Mockito.any(Recurso.class))).thenReturn(Mono.just(recurso));
        var response = createUseCase.apply(recursoDto);

        Assertions.assertEquals(Objects.requireNonNull(response.block()), recurso.getNombre());
        Mockito.verify(recursoRepository).save(Mockito.any());
    }
}
