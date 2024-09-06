package com.CestaSolidaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.user.dependente.dto.DataDeteilsDependente;
import com.CestaSolidaria.domain.user.dependente.dto.DataRegisterDependente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dependente")
public class DependenteController {
	
    @Transactional
    @PostMapping
    public ResponseEntity<DataDeteilsDependente> cadastroDependente(@RequestBody @Valid DataRegisterDependente data) {
        return null ;
    }

}
