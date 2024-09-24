package com.CestaSolidaria.domain.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserRepository;
import com.CestaSolidaria.domain.user.admin.dto.DataRegisterVistoria;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.admin.historicocredito.HistoricoCredito;
import com.CestaSolidaria.domain.user.admin.historicocredito.HistoricoCreditoService;
import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;
import com.CestaSolidaria.domain.user.dependente.DependenteService;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.enums.TipoBeneficio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DependenteService dependenteService;
	
	@Autowired
	private HistoricoCreditoService historicoCreditoService;
	
	public Page<DataStatusUser> statusUsuario (Pageable pageable,Status status) {
		
		Page<User> users = userRepository.findByStatus(status, pageable);
	    Page<DataStatusUser> data = users.map(DataStatusUser::new);	
		return data;
	}

	public ResponseEntity<DataDeteilsUser> vistoria(@Valid DataRegisterVistoria data) {
		
		User user = userRepository.findById(data.id())
								  .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
		if(data.status() == Status.APROVADO) {
	        user.setTipoBeneficio(tipoBeneficio(user.getRendaTotal(), dependenteService.todosDependentes(user.getCpf()).size()));
		}
		user.setStatus(data.status());
		userRepository.save(user);
		return ResponseEntity.ok(new DataDeteilsUser(user));
	}
	
	private TipoBeneficio tipoBeneficio(double rendaTotal, int dependentesTotal) {
		double rendaPercapita = rendaTotal/dependentesTotal;
		int tipoBeneficio = (rendaPercapita < 600) ? 1 : (rendaPercapita < 1200) ? 2 : 3;
		return TipoBeneficio.fromValor(tipoBeneficio);
	}

	public Page<DataHistoricoCredito> historicoCredito(Pageable pageable) {

	    Page<HistoricoCredito> historicoCreditoPage = historicoCreditoService.historicoCredito(pageable);

	    return historicoCreditoPage.map(h -> new DataHistoricoCredito(
	        h.getUsuarioId(),
	        h.getTipo(),
	        h.getValor(),
	        h.getDescricao()
	    ));
	}

}
