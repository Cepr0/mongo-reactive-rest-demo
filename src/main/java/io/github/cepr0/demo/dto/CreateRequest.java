package io.github.cepr0.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonDeserialize(builder = CreateRequest.CreateRequestBuilder.class)
public class CreateRequest {
	@NotBlank private String name;
	@NotBlank private String email;
	@NotNull private Integer age;

	@JsonPOJOBuilder(withPrefix = "")
	static final class CreateRequestBuilder {
	}
}
