package com.CestaSolidaria.domain.user.admin.historicocredito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;

@Service
public class HistoricoCreditoService {
	
	private HistoricoCreditoRepository historicoCreditoRepository;
	
	public void registro (DataHistoricoCredito data){
		
		HistoricoCredito historico = new HistoricoCredito(data);
		
		historicoCreditoRepository.save(historico);
	}
	
	public Page<HistoricoCredito> historicoCredito (Pageable page) {
		return historicoCreditoRepository.findAll(page);
	}

}
