package io.github.cepr0.demo.api;

import io.github.cepr0.demo.dto.CreateRequest;
import io.github.cepr0.demo.dto.PersonDto;
import io.github.cepr0.demo.dto.UpdateRequest;
import io.github.cepr0.demo.service.PersonService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("people")
public class PersonController {

    private static final String SORT_DELIMITER = ",";
    private final PersonService personService;

    public PersonController(@NonNull final PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonDto> create(@Valid @RequestBody CreateRequest request) {
        return personService.create(request);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<PersonDto>> update(@Valid @RequestBody UpdateRequest request, @PathVariable("id") String id) {
        return personService
                .update(request, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return personService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonDto>> getOne(@PathVariable("id") String id) {
        return personService
                .getOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<PersonDto> getAll(
            @NonNull @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @NonNull @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            @Nullable @RequestParam(value = "sort", required = false) String[] sort
    ) {
        var pageRequest = PageRequest.of(page, size, parseParameterIntoSort(sort));
        return personService.getAll(pageRequest);
    }

    /**
     * org.springframework.data.web.SortHandlerMethodArgumentResolver#parseParameterIntoSort
     */
    private Sort parseParameterIntoSort(@Nullable String[] source) {
        if (source == null) return Sort.unsorted();

        List<Sort.Order> allOrders = new ArrayList<>();
        for (String part : source) {
            if (part == null) continue;
            String[] elements = part.split(SORT_DELIMITER);
            Optional<Sort.Direction> direction = elements.length == 0 ?
                    Optional.empty() :
                    Sort.Direction.fromOptionalString(elements[elements.length - 1]);

            int lastIndex = direction.map(it -> elements.length - 1).orElseGet(() -> elements.length);

            for (int i = 0; i < lastIndex; i++) {
                toOrder(elements[i], direction).ifPresent(allOrders::add);
            }
        }
        return allOrders.isEmpty() ? Sort.unsorted() : Sort.by(allOrders);
    }

    /**
     * org.springframework.data.web.SortHandlerMethodArgumentResolver#toOrder(java.lang.String, java.util.Optional)
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static Optional<Sort.Order> toOrder(String property, Optional<Sort.Direction> direction) {
        if (!StringUtils.hasText(property)) return Optional.empty();
        return Optional.of(direction.map(it -> new Sort.Order(it, property)).orElseGet(() -> Sort.Order.by(property)));
    }
}
