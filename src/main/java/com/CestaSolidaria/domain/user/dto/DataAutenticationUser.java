package com.CestaSolidaria.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record DataAutenticationUser(
		@NotBlank
		String cpf,
		@NotBlank
		String password) {}
