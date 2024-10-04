package com.CestaSolidaria.domain.user.admin.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.dependente.dto.DataDeteilsDependente;
import com.CestaSolidaria.domain.user.enums.Situacao;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

public record DataStatusUser(Long id,
							 String nome,
							 String cpf,
							 String telefone,
							 double rendaTotal,
							 double rendaPercapita,
							 Situacao situacao,
							 Status status,
							 LocalDateTime criadoEm,
							 DataRegisterResidencia residencia,
							 List<DataDeteilsDependente> dependentes
							 ) {
	
	public DataStatusUser(User user) {
		this(user.getId(),
			 user.getNome(),
			 user.getCpf(),
			 user.getTelefone(),
			 user.getRendaTotal(),
			 calcularRendaPerCapita(user.getRendaTotal(),
					 				user.getDependentes().size()),
			 user.getSituacao(),
			 user.getStatus(),
			 user.getCriadoEm(),
			 new DataRegisterResidencia(user.getResidencia()),
			 user.getDependentes().stream()
			 					  .map(DataDeteilsDependente::new)
			 					  .collect(Collectors.toList()));
	}
	
    private static double calcularRendaPerCapita(double rendaTotal,
    											 int quantidadeDependentes) {
        int totalPessoas = quantidadeDependentes + 1;
        return rendaTotal / totalPessoas;
    }
	
}
