package io.github.cepr0.demo.service.impl;

import io.github.cepr0.demo.dto.CreateRequest;
import io.github.cepr0.demo.dto.PersonDto;
import io.github.cepr0.demo.dto.UpdateRequest;
import io.github.cepr0.demo.model.Person;
import io.github.cepr0.demo.service.PersonMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class PersonMapperImpl implements PersonMapper {

    @NonNull
    @Override
    public Person fromCreateRequest(@NonNull final CreateRequest request) {
        return Person.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();
    }

    @NonNull
    @Override
    public Person fromUpdateRequest(@NonNull final Person person, @NonNull final UpdateRequest request) {
        ofNullable(request.getName()).ifPresent(person::setName);
        ofNullable(request.getEmail()).ifPresent(person::setEmail);
        ofNullable(request.getAge()).ifPresent(person::setAge);
        return person;
    }

    @NonNull
    @Override
    public PersonDto toPersonDto(@NonNull final Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .age(person.getAge())
                .build();
    }
}
