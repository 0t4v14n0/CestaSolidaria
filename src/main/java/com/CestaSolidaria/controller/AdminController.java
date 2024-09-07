package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.user.admin.UserAdminService;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.enums.Status;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserAdminService userAdminService;
	
	@GetMapping("/{status}")
	public ResponseEntity<Page<DataStatusUser>> buscaPorStatus(@PathVariable Status status,@PageableDefault(size = 10,
			  																								sort = {"id"}) Pageable pageable){
		return ResponseEntity.ok(userAdminService.statusUsuario(pageable, status));	
	}

}
