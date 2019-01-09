package io.github.cepr0.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = UpdateRequest.UpdateRequestBuilder.class)
public class UpdateRequest {
	private String name;
	private String email;
	private Integer age;

	@JsonPOJOBuilder(withPrefix = "")
	static final class UpdateRequestBuilder {
	}
}
