package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.carrinho.dto.DataCarrinho;
import com.CestaSolidaria.domain.carrinho.item.CarrinhoItemService;
import com.CestaSolidaria.domain.carrinho.item.dto.DataItem;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoItemService carrinhoItemService;
	
	@Transactional
	@PostMapping("/additem")
	public ResponseEntity<DataCarrinho> addItem(@Valid DataItem data,
											Authentication authentication){
		carrinhoItemService.addItemCarrinho(data, authentication.getName());
		return ResponseEntity.ok(new DataCarrinho(carrinhoItemService.addItemCarrinho(data, authentication.getName())));
	}
	
//	@Transactional
//	@PostMapping("/removeitem")
//	public ResponseEntity<DataCarrinho> removeItem(@Valid DataItem data,
//											Authentication authentication){
//		carrinhoItemService.retiraItemCarrinho(data, authentication.getName());
//		return ResponseEntity.ok().body("Item retirado.");
//	}
//	
//	@Transactional
//	@PostMapping("/finalizar")
//	public ResponseEntity<DataCarrinho> finalizar(@Valid DataItem data,
//											Authentication authentication){
//		carrinhoItemService.addItemCarrinho(data, authentication.getName());
//		return ResponseEntity.ok();
//	}

}
