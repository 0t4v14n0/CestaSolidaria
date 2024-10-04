package com.CestaSolidaria.domain.user.admin.historicocredito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoricoCreditoService {
	
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	
	public void registro (DataHistoricoCredito data){
		
		HistoricoCredito historico = new HistoricoCredito(data);
		
		historicoCreditoRepository.save(historico);
	}
	
	public Page<HistoricoCredito> historicoCredito (Pageable page) {
		
		Page<HistoricoCredito> historicoCreditoPage = historicoCreditoRepository.findAll(page);
		
	    if (historicoCreditoPage.isEmpty()) {
	        throw new EntityNotFoundException("Nao ha historico de creditos.");
	    }
	    
		return historicoCreditoPage;
	}

}
