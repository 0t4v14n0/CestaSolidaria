package com.CestaSolidaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CestaSolidaria.domain.produto.ProdutoService;
import com.CestaSolidaria.domain.produto.dto.DataDeteilsProduto;
import com.CestaSolidaria.domain.produto.dto.DataRegisterProduto;
import com.CestaSolidaria.domain.produto.dto.DataUpdatedProduto;
import com.CestaSolidaria.domain.user.admin.UserAdminService;
import com.CestaSolidaria.domain.user.admin.dto.DataRegisterVistoria;
import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;
import com.CestaSolidaria.domain.user.enums.Status;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserAdminService userAdminService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{status}")
	public ResponseEntity<Page<DataStatusUser>> buscaPorStatus(@PathVariable Status status,@PageableDefault(size = 10,
			  																								sort = {"id"}) Pageable pageable){
		return ResponseEntity.ok(userAdminService.statusUsuario(pageable, status));	
	}
	
	@Transactional
    @PostMapping
    public ResponseEntity<?> vistoria(@Valid @RequestBody DataRegisterVistoria data){
		return userAdminService.vistoria(data);	
	}
	
	@GetMapping
	public ResponseEntity<Page<DataHistoricoCredito>> HistoricoCredito(@PageableDefault(size = 10,
															   sort = {"id"}) Pageable pageable) {
		return ResponseEntity.ok(userAdminService.historicoCredito(pageable));
	}
	
	@Transactional
    @PostMapping("/produto/novo")
    public ResponseEntity<DataDeteilsProduto> novoProduto(@Valid @RequestBody DataRegisterProduto produto){
		return ResponseEntity.ok(produtoService.addProduto(produto));
	}
	
    @Transactional
    @PutMapping("/produto/atualizar/{id}")
    public ResponseEntity<DataDeteilsProduto> updateProduto(@PathVariable Long id, @RequestBody DataUpdatedProduto produto){
		return ResponseEntity.ok(produtoService.updatedProduto(id, produto));
    }
    
    @Transactional
    @PutMapping("/produto/atualizar/{id}/{q}")
    public ResponseEntity<DataDeteilsProduto> atualizarEstoqueProduto(@PathVariable Long id,
    																  @PathVariable int q,
    																  @RequestBody DataUpdatedProduto produto){
		return ResponseEntity.ok(produtoService.atualizarEstoqueProduto(id, q, produto));
    }
    
    
    

}
