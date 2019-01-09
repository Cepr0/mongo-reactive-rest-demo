package io.github.cepr0.demo.service;

import io.github.cepr0.demo.dto.CreateRequest;
import io.github.cepr0.demo.dto.PersonDto;
import io.github.cepr0.demo.dto.UpdateRequest;
import io.github.cepr0.demo.model.Person;
import org.springframework.lang.NonNull;

public interface PersonMapper {
    @NonNull Person fromCreateRequest(@NonNull CreateRequest request);
    @NonNull Person fromUpdateRequest(@NonNull Person person, @NonNull UpdateRequest request);
    @NonNull PersonDto toPersonDto(@NonNull Person person);
}
