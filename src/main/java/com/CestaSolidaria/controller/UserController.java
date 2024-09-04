package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserRepository;
import com.CestaSolidaria.domain.user.UserService;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService userService;
	
    @PostMapping
    @Transactional
    public ResponseEntity<DataDeteilsUser> register(@RequestBody @Valid DataRegisterUser data,
    												UriComponentsBuilder uriBuilder ) {	
    	var user = new User(data);
    	user.setSenha(userService.passwordCrypt(data.senha()));   	
    	repository.save(user);    	
    	var uri = uriBuilder.path("").buildAndExpand(user.getId()).toUri();
    	return ResponseEntity.created(uri).body(new DataDeteilsUser(user));		
    }

}
