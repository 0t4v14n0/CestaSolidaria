package com.CestaSolidaria.domain.user.residencia.dto;

public record DataRegisterResidencia(String endereco,
									 String cidade,
									 String estado,
									 String cep,
									 String pais,
									 String referencia) {}
