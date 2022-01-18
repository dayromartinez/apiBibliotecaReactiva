package com.sofka.biblioteca.UseCases;


import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
public class PrestarRecursoUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;
    @SpyBean
    private PrestarRecursoUseCase prestarRecursoUseCase;

    @Test
    void prestarRecurso(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(true);
        recurso.setFechaPrestamo("01/01/2022");

        Mockito.when(recursoRepository.findById(Mockito.anyString())).thenReturn(Mono.just(recurso));

        var response = prestarRecursoUseCase.apply("1111");

        Assertions.assertEquals(response.block(), "El recurso consultado ya se encuentra en préstamo");
        Mockito.verify(recursoRepository).findById(Mockito.anyString());

    }
}
