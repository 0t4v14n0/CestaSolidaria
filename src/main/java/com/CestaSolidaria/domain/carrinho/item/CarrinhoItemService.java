package com.CestaSolidaria.domain.carrinho.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.carrinho.Carrinho;
import com.CestaSolidaria.domain.carrinho.CarrinhoService;
import com.CestaSolidaria.domain.carrinho.item.dto.DataItem;
import com.CestaSolidaria.domain.produto.Produto;
import com.CestaSolidaria.domain.produto.ProdutoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarrinhoItemService {
	
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Carrinho addItemCarrinho (DataItem data,String cpf) {
		
		Carrinho carrinho = carrinhoService.getCarrinhoAbertoUser(cpf);
				
		Produto produto = produtoService.getProduto(data.idProduto());
		
		CarrinhoItem item = new CarrinhoItem();
		
		if (produto == null) {
			new EntityNotFoundException("Produto nao encontrado !");
		}
		
		item.setCarrinho(carrinhoService.getCarrinhoAbertoUser(cpf));
		item.setProduto(produto);
		item.setQuantidade(data.quantidade());
		item.setPreco(produto.getPreco());
		
		carrinhoItemRepository.save(item);
		
		carrinho.setTotal(carrinho.getTotal() + produto.getPreco() * data.quantidade());
		
		carrinhoService.save(carrinho);
		
		return carrinho;
	}
	
	public Carrinho retiraItemCarrinho (DataItem data,String cpf) {
		return null;
	}

}
