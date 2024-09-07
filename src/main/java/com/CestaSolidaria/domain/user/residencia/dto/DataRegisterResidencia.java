package com.CestaSolidaria.domain.user.residencia.dto;

import com.CestaSolidaria.domain.user.residencia.Residencia;

public record DataRegisterResidencia(String endereco,
									 String cidade,
									 String estado,
									 String cep,
									 String pais,
									 String referencia) {
	
	public DataRegisterResidencia(Residencia residencia) {
		this(residencia.getEndereco(),residencia.getCidade(),residencia.getEstado(),residencia.getCep(),residencia.getPais(),residencia.getReferencia());
	}
}
