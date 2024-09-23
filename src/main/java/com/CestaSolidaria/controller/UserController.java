package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.CestaSolidaria.domain.user.UserService;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.dto.DataAutenticationUser;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Transactional
    @PostMapping("/register")
    public ResponseEntity<DataDeteilsUser> register(@RequestBody @Valid DataRegisterUser data,
    												UriComponentsBuilder uriBuilder ) {
    	return userService.registerUsuario(data, uriBuilder);		
    }
	
    @Transactional
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DataAutenticationUser data) {
        return userService.login(data);
    }
    
    @Transactional
    @PutMapping
    public ResponseEntity<DataStatusUser> atualizarUser(@RequestBody @Valid DataRegisterUser data,
    													 Authentication authentication){
		return ResponseEntity.ok(userService.atualizarUser(data,authentication.getName()));
    }
    
    @GetMapping
    public ResponseEntity<DataDeteilsUser> detalheUsuario(Authentication authentication){
		return ResponseEntity.ok(new DataDeteilsUser(userService.buscaUsuario(authentication.getName())));
    }
    
}
