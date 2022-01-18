package com.sofka.biblioteca.UseCases;

import com.sofka.biblioteca.Collection.Recurso;
import com.sofka.biblioteca.Mapper.RecursoMapper;
import com.sofka.biblioteca.Repository.RecursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class GetUseCaseTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @SpyBean
    private GetUseCase getUseCase;

    @Test
    void getTest(){

        var recurso = new Recurso();
        recurso.setId("1111");
        recurso.setNombre("Cien años de soledad");
        recurso.setTipoRecurso("Libro");
        recurso.setAreaTematica("Literatura");
        recurso.setEnPrestamo(false);
        recurso.setFechaPrestamo("");

        Mockito.when(recursoRepository.findById(Mockito.anyString())).thenReturn(Mono.just(recurso));

        var response = getUseCase.apply("1111");

        Assertions.assertEquals(response.block().getNombre(), recurso.getNombre());
        Assertions.assertEquals(response.block().getId(), "1111");
        Assertions.assertEquals(response.block().getTipoRecurso(), recurso.getTipoRecurso());
        Assertions.assertEquals(response.block().getAreaTematica(), recurso.getAreaTematica());
        Assertions.assertEquals(response.block().getEnPrestamo(), recurso.getEnPrestamo());
        Mockito.verify(recursoRepository).findById(Mockito.anyString());
    }
}
