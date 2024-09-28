package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.produto.ProdutoService;
import com.CestaSolidaria.domain.produto.dto.DataDeteilsProduto;

@RestController
@RequestMapping("/publico")
public class DadosPublicosController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/produtos")
    public ResponseEntity<Page<DataDeteilsProduto>> totalMercadoria(@PageableDefault(size = 10,
			   																		 sort = {"id"}) Pageable pageable){
		return ResponseEntity.ok(produtoService.totosProdutos(pageable));
	}
	
	@GetMapping("/pix")
    public ResponseEntity<?> qrPix(){
		return null;
	}

}
