package com.CestaSolidaria.domain.carrinho.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.carrinho.Carrinho;
import com.CestaSolidaria.domain.carrinho.CarrinhoService;
import com.CestaSolidaria.domain.carrinho.dto.DataCarrinho;
import com.CestaSolidaria.domain.carrinho.item.dto.DataItem;
import com.CestaSolidaria.domain.produto.Produto;
import com.CestaSolidaria.domain.produto.ProdutoService;

import jakarta.validation.Valid;

@Service
public class CarrinhoItemService {
	
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public DataCarrinho addItemCarrinho (DataItem data,String cpf) {
		
		Carrinho carrinho = carrinhoService.getCarrinhoAbertoUser(cpf);
				
		Produto produto = produtoService.getProduto(data.idProduto());
		
		CarrinhoItem item = new CarrinhoItem();
		
		item.setCarrinho(carrinho);
		item.setProduto(produto);
		item.setQuantidade(data.quantidade());
		item.setPreco(produto.getPreco() * data.quantidade());
		
		carrinhoItemRepository.save(item);
		
		carrinho.setTotal(carrinho.getTotal() + produto.getPreco() * data.quantidade());
		
		carrinhoService.save(carrinho);
		
		return new DataCarrinho(carrinho);
	}
	
	public DataCarrinho retiraItemCarrinho (Long idProduto,String cpf) {
		
		Carrinho carrinho = carrinhoService.getCarrinhoAbertoUser(cpf);
		
		List<CarrinhoItem> itens = carrinho.getItens();
		
	    boolean itemRemovido = itens.removeIf(item -> item.getProduto()
	    												  .getId()
	    												  .equals(idProduto));
	    if (itemRemovido) {
	        carrinho.setItens(itens);
	        carrinho.setTotal(carrinhoService.valorTotalCarrinho(carrinho));
	        carrinhoService.save(carrinho);
	    }
		
		return new DataCarrinho(carrinho);
	}

	public DataCarrinho atualizarQuantidadeItem (@Valid DataItem data, String cpf) {
		
		Carrinho carrinho = carrinhoService.getCarrinhoAbertoUser(cpf);
		
		List<CarrinhoItem> itens = carrinho.getItens();	
		
		Optional<CarrinhoItem> itemOptional = itens.stream()
			    								   .filter(item -> item.getProduto().getId().equals(data.idProduto()))
			    								   .findFirst();

		itemOptional.ifPresent(item -> {
			item.setQuantidade(data.quantidade());
			
			double novoPreco = data.quantidade() * item.getProduto().getPreco();
			item.setPreco(novoPreco);
		});
		
			
		carrinho.setItens(itens);
			
		carrinhoService.save(carrinho);
			
		return new DataCarrinho(carrinho);
	}

}
