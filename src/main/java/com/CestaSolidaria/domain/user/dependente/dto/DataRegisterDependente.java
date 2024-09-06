package com.CestaSolidaria.domain.user.dependente.dto;

import com.CestaSolidaria.domain.user.enums.Situacao;

public record DataRegisterDependente(String nome,
									 Situacao situacao,
									 String dataNascimento,
									 String cpf) {}
