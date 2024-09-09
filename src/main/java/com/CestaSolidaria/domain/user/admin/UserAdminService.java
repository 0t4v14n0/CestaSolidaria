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
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.enums.Status;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<DataStatusUser> statusUsuario (Pageable pageable,Status status) {
		
	    Page<User> users = userRepository.findByStatus(status, pageable);
	    Page<DataStatusUser> data = users.map(DataStatusUser::new);	
		return data;
	}

	public ResponseEntity<DataDeteilsUser> vistoria(@Valid DataRegisterVistoria data) {
		
		User user = userRepository.findById(data.id())
								  .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
		user.setStatus(data.status());
		userRepository.save(user);
		return ResponseEntity.ok(new DataDeteilsUser(user));
	}

}
