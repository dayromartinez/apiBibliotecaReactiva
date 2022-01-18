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

@SpringBootTest
public class DeleteUseCaseTest {

    @MockBean
    RecursoRepository recursoRepository;

    @SpyBean
    DeleteUseCase deleteUseCase;

    @Test
    void deleteTest(){

        var recursoDto = new RecursoDTO();
        recursoDto.setId("1111");
        recursoDto.setNombre("Cien años de soledad");
        recursoDto.setTipoRecurso("Libro");
        recursoDto.setAreaTematica("Literatura");
        recursoDto.setEnPrestamo(false);
        recursoDto.setFechaPrestamo("");

        Mockito.when(recursoRepository.deleteById(recursoDto.getId())).thenReturn(Mono.empty());

        var response = deleteUseCase.apply(recursoDto.getId()).thenReturn(Mono.empty());

        Assertions.assertEquals(response.block(), Mono.empty());
        Mockito.verify(recursoRepository).deleteById(recursoDto.getId());
    }
}
