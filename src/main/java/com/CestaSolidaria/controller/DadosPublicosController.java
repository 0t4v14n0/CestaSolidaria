package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.pix.PixService;
import com.CestaSolidaria.domain.pix.dto.DataPix;
import com.CestaSolidaria.domain.pix.dto.DataQr;
import com.CestaSolidaria.domain.produto.ProdutoService;
import com.CestaSolidaria.domain.produto.dto.DataDeteilsProduto;

@RestController
@RequestMapping("/publico")
public class DadosPublicosController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PixService pixService;
	
	@GetMapping("/produtos")
    public ResponseEntity<Page<DataDeteilsProduto>> totalMercadoria(@PageableDefault(size = 10,
			   																		 sort = {"id"}) Pageable pageable){
		return ResponseEntity.ok(produtoService.totosProdutos(pageable));
	}
	
	@PostMapping("/pix")
    public ResponseEntity<DataQr> qrDoarPix(@RequestBody DataPix data){
		return ResponseEntity.ok(pixService.gerarQR(data));
	}

}
