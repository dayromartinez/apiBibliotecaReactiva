package com.sofka.biblioteca.Router;


import com.sofka.biblioteca.DTO.RecursoDTO;
import com.sofka.biblioteca.UseCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> getAll(ListUseCase listUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), RecursoDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                RecursoDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getByName(GetByNameUseCase getByNameUseCase) {
        return route(
                GET("/getByName/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getByNameUseCase.apply(
                                        request.pathVariable("name")),
                                RecursoDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> create(CreateUseCase createUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO ->  createUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> update(UpdateUseCase updateUseCase) {
        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO ->  updateUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                PUT("/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteUseCase deleteUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> consultarDisponibilidad(DisponibilidadRecursoUseCase disponibilidadRecursoUseCase) {
        return route(
                GET("/recurso/disponibilidad/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(disponibilidadRecursoUseCase.apply(
                                        request.pathVariable("name")),
                                String.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(PrestarRecursoUseCase prestarRecursoUseCase) {
        return route(
                PUT("/recurso/prestar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(prestarRecursoUseCase.apply(
                                        request.pathVariable("id")),
                                String.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> devolverRecurso(DevolverRecursoUseCase devolverRecursoUseCase) {
        return route(
                PUT("/recurso/devolver/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(devolverRecursoUseCase.apply(
                                        request.pathVariable("id")),
                                String.class
                        ))
        );
    }

}
