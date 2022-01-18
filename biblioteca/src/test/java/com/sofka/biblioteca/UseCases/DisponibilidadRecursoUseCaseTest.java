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
public class DisponibilidadRecursoUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;
    @SpyBean
    private DisponibilidadRecursoUseCase disponibilidadRecursoUseCase;

    @Test
    void consultarDisponibilidad(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");

        Mockito.when(recursoRepository.findByNombre(Mockito.anyString())).thenReturn(Mono.just(recurso));

        var response = disponibilidadRecursoUseCase.apply("Cien años de soledad");

        Assertions.assertEquals(response.block(), "El recurso consultado se encuentra disponible para su préstamo");
        Mockito.verify(recursoRepository).findByNombre(Mockito.anyString());

    }
}
