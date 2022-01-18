package com.sofka.biblioteca.Repository;

import com.sofka.biblioteca.Collection.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RecursoRepository extends ReactiveMongoRepository<Recurso, String> {
    Mono<Recurso> findByNombre(String nombre);
}
