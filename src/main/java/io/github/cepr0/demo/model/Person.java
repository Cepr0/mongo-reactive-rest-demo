package io.github.cepr0.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("people")
@Data
@Builder(toBuilder = true)
public class Person implements Serializable {
	@Id private String id;
	@Indexed private String name;
	@Indexed(unique = true) private String email;
	@Indexed private Integer age;
}
