package com.CestaSolidaria.domain.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserRepository;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.dependente.DependenteRepository;
import com.CestaSolidaria.domain.user.enums.Status;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DependenteRepository dependenteRepository;
	
	public Page<DataStatusUser> statusUsuario (Pageable pageable,Status status) {
		
	    Page<User> users = userRepository.findByStatus(status, pageable);
	    Page<DataStatusUser> data = users.map(DataStatusUser::new);	
		return data;
	}

	public ResponseEntity<?> vistoria(@Valid DataRegisterVistoria data) {
		
		User user = userRepository.findById(data.id())
								  .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
		
		switch (data.status()) {
		case APROVADO:
			user.setCreditos(calculoCredito(user.getRendaTotal(),dependenteRepository.findAllByIdAndUser(user).size()));
			break;
		default:
			break;
		}
		return null;
	}
	
	private double calculoCredito(double rendaTotal, int dependentes) {
		double rendaPercapita = rendaTotal/dependentes;
		int faixaRenda = (rendaPercapita < 600) ? 1 : (rendaPercapita < 1200) ? 2 : 3;
		switch(rendaPercapita) {
		case 600:
			break;
		default:
			break;
		}
		return 0;
	}

}
