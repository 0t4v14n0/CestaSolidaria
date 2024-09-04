package com.CestaSolidaria.domain.user.dto;

import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterUser(
		@NotBlank
		String nome,
		@NotBlank
        @Email
		String cpf,
		@NotBlank
		String senha,
		@NotBlank
		String telefone,
		@NotBlank
		DataRegisterResidencia residencia) {}
