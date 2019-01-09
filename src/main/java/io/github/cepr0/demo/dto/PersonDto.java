package io.github.cepr0.demo.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder(toBuilder = true)
public class PersonDto implements Serializable {
	private String id;
	private String name;
	private String email;
	private Integer age;
}
