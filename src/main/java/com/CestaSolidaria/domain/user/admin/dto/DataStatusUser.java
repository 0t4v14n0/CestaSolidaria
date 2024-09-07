package com.CestaSolidaria.domain.user.admin.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.dependente.dto.DataDeteilsDependente;
import com.CestaSolidaria.domain.user.enums.Situacao;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

public record DataStatusUser(String nome,
							 String cpf,
							 String telefone,
							 Situacao situacao,
							 Status status,
							 LocalDateTime criadoEm,
							 DataRegisterResidencia residencia,
							 List<DataDeteilsDependente> dependentes
							 ) {
	
	public DataStatusUser(User user) {
		this(user.getNome(), user.getCpf(), user.getTelefone(), user.getSituacao(), user.getStatus(), user.getCriadoEm(),
			 new DataRegisterResidencia(user.getResidencia()),
			 user.getDependentes().stream()
            					  .map(DataDeteilsDependente::new)
            					  .collect(Collectors.toList()));
	}
	
}
