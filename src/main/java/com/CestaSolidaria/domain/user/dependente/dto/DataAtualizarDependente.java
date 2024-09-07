package com.CestaSolidaria.domain.user.dependente.dto;

import com.CestaSolidaria.domain.user.enums.Situacao;

public record DataAtualizarDependente(long id,
									  String nome,
									  String cpf,
									  String dataNascimento,
									  Situacao situacao) {

}
