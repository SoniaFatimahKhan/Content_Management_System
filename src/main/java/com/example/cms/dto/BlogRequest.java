package com.example.cms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class BlogRequest {
	@NotNull(message = "Title cannot be null")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Title must contain only alphabet characters")
	private String title;
	@NotNull
	@Pattern(regexp = "^[A-Za-z\\s,]+$", message = "Topics must contain only alphabet characters separated by commas")
	private String topics;
	@Null
	@Pattern(regexp = "^[A-Za-z0-9\\s.,!?]+$", message = "About must contain only alphanumeric characters, spaces, and punctuation")
	private String about;
}

