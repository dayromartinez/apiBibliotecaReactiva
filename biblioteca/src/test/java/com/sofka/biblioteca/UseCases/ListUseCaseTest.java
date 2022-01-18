package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ListUseCaseTest {

    RecursoRepository recursoRepository;
    ListUseCase listUseCase;

    @BeforeEach
    public void setup(){
        RecursoMapper recursoMapper = new RecursoMapper();
        recursoRepository = mock(RecursoRepository.class);
        listUseCase = new ListUseCase(recursoRepository, recursoMapper);
    }

    @Test
    void getValidationTest(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");
        when(recursoRepository.findAll()).thenReturn(Flux.just(recurso));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(recursoDTO -> {
                    assert recursoDTO.getId().equals("1111");
                    assert recursoDTO.getNombre().equals(recurso.getNombre());
                    assert recursoDTO.getTipoRecurso().equals(recurso.getTipoRecurso());
                    assert recursoDTO.getAreaTematica().equals(recurso.getAreaTematica());
                    assert recursoDTO.getEnPrestamo().equals(recurso.getEnPrestamo());
                    assert recursoDTO.getFechaPrestamo().equals(recurso.getFechaPrestamo());
                    return true;
                })
                .verifyComplete();

        verify(recursoRepository).findAll();

    }
}
