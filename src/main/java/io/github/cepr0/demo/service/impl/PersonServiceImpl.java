package io.github.cepr0.demo.service.impl;

import io.github.cepr0.demo.dto.CreateRequest;
import io.github.cepr0.demo.dto.PersonDto;
import io.github.cepr0.demo.dto.UpdateRequest;
import io.github.cepr0.demo.repo.PersonRepo;
import io.github.cepr0.demo.service.PersonMapper;
import io.github.cepr0.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;
    private final PersonMapper personMapper;

    public PersonServiceImpl(@NonNull final PersonRepo personRepo, @NonNull final PersonMapper personMapper) {
        this.personRepo = personRepo;
        this.personMapper = personMapper;
    }

    @NonNull
    @Override
    public Mono<PersonDto> create(@NonNull final CreateRequest request) {
        return personRepo
                .save(personMapper.fromCreateRequest(request))
                .map(personMapper::toPersonDto);
    }

    @NonNull
    @Override
    public Mono<PersonDto> update(@NonNull final UpdateRequest request, @NonNull final String id) {
        return personRepo
                .findById(id)
                .map(person -> personMapper.fromUpdateRequest(person, request))
                .flatMap(personRepo::save)
                .map(personMapper::toPersonDto);
    }

    @NonNull
    @Override
    public Mono<Void> delete(@NonNull final String id) {
        return personRepo.deleteById(id);
    }

    @NonNull
    @Override
    public Mono<PersonDto> getOne(@NonNull final String id) {
        return personRepo
                .findById(id)
                .map(personMapper::toPersonDto);
    }

    @NonNull
    @Override
    public Flux<PersonDto> getAll() {
        return personRepo
                .findAll()
                .map(personMapper::toPersonDto);
    }

    @NonNull
    @Override
    public Flux<PersonDto> getAll(@NonNull final Pageable pageable) {
        return personRepo
                .findAllBy(pageable)
                .map(personMapper::toPersonDto);
    }
}
