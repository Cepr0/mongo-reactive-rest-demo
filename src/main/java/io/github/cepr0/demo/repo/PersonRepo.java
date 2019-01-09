package io.github.cepr0.demo.repo;

import io.github.cepr0.demo.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

public interface PersonRepo extends ReactiveMongoRepository<Person, String> {
//    @Query("{ id: { $exists: true }}")
    Flux<Person> findAllBy(@NonNull Pageable page);
}
