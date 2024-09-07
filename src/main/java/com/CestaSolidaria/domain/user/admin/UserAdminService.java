package com.CestaSolidaria.domain.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserRepository;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.enums.Status;

@Service
public class UserAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<DataStatusUser> statusUsuario (Pageable pageable,Status status) {
		
	    Page<User> users = userRepository.findByStatus(status, pageable);
	    Page<DataStatusUser> data = users.map(DataStatusUser::new);	
		return data;
	}

}
