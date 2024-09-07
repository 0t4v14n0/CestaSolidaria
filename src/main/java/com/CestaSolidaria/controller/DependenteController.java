package com.CestaSolidaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.user.dependente.DependenteService;
import com.CestaSolidaria.domain.user.dependente.dto.DataAtualizarDependente;
import com.CestaSolidaria.domain.user.dependente.dto.DataDeteilsDependente;
import com.CestaSolidaria.domain.user.dependente.dto.DataRegisterDependente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dependente")
public class DependenteController {
	
	@Autowired
	private DependenteService dependenteService;
	
    @Transactional
    @PostMapping
    public ResponseEntity<DataDeteilsDependente> cadastroDependente(@RequestBody @Valid DataRegisterDependente data,
    																					Authentication authentication) {
        return ResponseEntity.ok(dependenteService.addDependente(data, authentication.getName()));
    }
    
    @Transactional
    @PutMapping
    public ResponseEntity<DataDeteilsDependente> updatedDependente(DataAtualizarDependente data, Authentication authentication){
    	return ResponseEntity.ok(dependenteService.atualizarDependente(data, authentication.getName()));
    }
    
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDependente(@PathVariable Long id, Authentication authentication){
    	dependenteService.deleteDependente(id, authentication.getName());
		return ResponseEntity.ok().body(200);
    }
    
    @GetMapping
    public ResponseEntity<List<DataDeteilsDependente>> todosDependentes(Authentication authentication){
		return ResponseEntity.ok(dependenteService.todosDependentes(authentication.getName()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DataDeteilsDependente> dependentesPorId(@PathVariable Long id, Authentication authentication){
		return ResponseEntity.ok(dependenteService.dependentesPorId(id, authentication.getName()));
    }
    
}
