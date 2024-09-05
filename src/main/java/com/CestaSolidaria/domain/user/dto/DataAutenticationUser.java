package com.CestaSolidaria.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataAutenticationUser(
		@NotBlank
		@Email
		String cpf,
		@NotBlank
		String password) {}
