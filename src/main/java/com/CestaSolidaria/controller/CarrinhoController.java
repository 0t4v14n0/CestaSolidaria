package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.carrinho.CarrinhoService;
import com.CestaSolidaria.domain.carrinho.dto.DataCarrinho;
import com.CestaSolidaria.domain.carrinho.item.CarrinhoItemService;
import com.CestaSolidaria.domain.carrinho.item.dto.DataItem;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoItemService carrinhoItemService;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Transactional
	@PostMapping("/item/add")
	public ResponseEntity<DataCarrinho> addItem(@Valid DataItem data,
											Authentication authentication){
		carrinhoItemService.addItemCarrinho(data, authentication.getName());
		return ResponseEntity.ok(carrinhoItemService.addItemCarrinho(data, authentication.getName()));
	}
	
	@Transactional
	@PostMapping("/item/remove/{idProduto}")
	public ResponseEntity<DataCarrinho> removeItem(@PathVariable Long idProduto,
											Authentication authentication){
		return ResponseEntity.ok(carrinhoItemService.retiraItemCarrinho(idProduto, authentication.getName()));
	}
	
	@Transactional
	@PostMapping("/item/quantidade")
	public ResponseEntity<DataCarrinho> atualizarQuantidadeItem(@Valid DataItem data,
											Authentication authentication){
		return ResponseEntity.ok(carrinhoItemService.atualizarQuantidadeItem(data, authentication.getName()));
	}
	
	@Transactional
	@PostMapping("/finalizar")
	public ResponseEntity<DataCarrinho> finalizar(Authentication authentication){
		return ResponseEntity.ok(carrinhoService.finalizarCarrinho(authentication.getName()));
	}
	
	@Transactional
	@PostMapping("/deletar")
	public ResponseEntity<?> deletar(Authentication authentication){
		carrinhoService.deletarCarrinho(authentication.getName());
		return ResponseEntity.ok("Carrinho deletado!");
	}

}
