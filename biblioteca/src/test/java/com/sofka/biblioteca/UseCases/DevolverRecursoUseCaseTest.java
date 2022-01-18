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
public class DevolverRecursoUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;
    @SpyBean
    private DevolverRecursoUseCase devolverRecursoUseCase;


    @Test
    void devolverRecurso(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");

        Mockito.when(recursoRepository.findById(Mockito.anyString())).thenReturn(Mono.just(recurso));

        var response = devolverRecursoUseCase.apply("1111");

        Assertions.assertEquals(response.block(), "El recurso consultado no se encuentra en préstamo. Inténtelo nuevamente");
        Mockito.verify(recursoRepository).findById(Mockito.anyString());
    }
}
