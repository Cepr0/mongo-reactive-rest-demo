package io.github.cepr0.demo.service;

import io.github.cepr0.demo.dto.CreateRequest;
import io.github.cepr0.demo.dto.PersonDto;
import io.github.cepr0.demo.dto.UpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    @NonNull Mono<PersonDto> create(@NonNull CreateRequest request);
    @NonNull Mono<PersonDto> update(@NonNull UpdateRequest request, @NonNull String id);
    @NonNull Mono<Void> delete(@NonNull String id);
    @NonNull Mono<PersonDto> getOne(@NonNull String id);
    @NonNull Flux<PersonDto> getAll();
    @NonNull Flux<PersonDto> getAll(@NonNull Pageable pageable);
}
