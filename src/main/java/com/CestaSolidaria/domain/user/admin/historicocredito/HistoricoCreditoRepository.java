package com.CestaSolidaria.domain.user.admin.historicocredito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoCreditoRepository extends JpaRepository<HistoricoCredito, Long> {
	
	Page findAll(Pageable page);

}
