package com.CestaSolidaria.domain.user.dependente.dto;

import com.CestaSolidaria.domain.user.dependente.Dependente;
import com.CestaSolidaria.domain.user.enums.Situacao;

public record DataDeteilsDependente(String nome,
									String cpf,
									String dataNascimento,
									Situacao situacao) {

	public DataDeteilsDependente(Dependente dep) {
		this(dep.getNome(),dep.getCpf(),dep.getDataNascimento(),dep.getSituacao());
	}
}
