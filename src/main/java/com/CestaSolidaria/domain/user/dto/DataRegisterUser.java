package com.CestaSolidaria.domain.user.dto;

import com.CestaSolidaria.domain.user.enums.Situacao;
import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterUser(@NotBlank
							   String nome,
							   @NotBlank
							   String cpf,
							   @NotBlank
							   String senha,
							   @NotBlank
							   String telefone,
							   @NotBlank
							   String dataNascimento,
							   
							   double rendaTotal,
							   
							   Situacao situacao,
							   @NotNull
							   DataRegisterResidencia residencia) {}
