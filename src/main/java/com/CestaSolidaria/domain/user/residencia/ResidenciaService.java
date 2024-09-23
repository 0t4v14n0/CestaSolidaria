package com.CestaSolidaria.domain.user.residencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

import jakarta.validation.constraints.NotNull;

@Service
public class ResidenciaService {
	
	@Autowired
	private ResidenciaRepository residenciaRepository;

	public Residencia atualiza(@NotNull DataRegisterResidencia data, Residencia reside) {
		
		Residencia residencia = residenciaRepository.findById(reside.getId()).get();
		
		if(data.endereco() != null && !data.endereco().isEmpty())residencia.setEndereco(data.endereco());
		if(data.cidade() != null && !data.cidade().isEmpty())residencia.setCidade(data.cidade());
		if(data.estado() != null && !data.estado().isEmpty())residencia.setEstado(data.estado());
		if(data.cep() != null && !data.cep().isEmpty())residencia.setCep(data.cep());
		if(data.pais() != null && !data.pais().isEmpty())residencia.setPais(data.pais());
		if(data.referencia() != null && !data.referencia().isEmpty())residencia.setReferencia(data.referencia());
		
		return residencia;
	}

	public void saveResidencia(Residencia residencia) {
		residenciaRepository.save(residencia);	
	}

}
