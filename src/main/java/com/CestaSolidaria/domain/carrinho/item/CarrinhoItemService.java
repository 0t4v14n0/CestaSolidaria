package com.CestaSolidaria.domain.carrinho.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.carrinho.CarrinhoService;
import com.CestaSolidaria.domain.carrinho.item.enums.DataAddItem;
import com.CestaSolidaria.domain.produto.ProdutoService;

@Service
public class CarrinhoItemService {
	
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public void addItemCarrinho (DataAddItem data,String cpf) {
		
		CarrinhoItem item = new CarrinhoItem();
		
		item.setCarrinhoId(carrinhoService.getCarrinhoAbertoUser(cpf));
		item.setProdutoId(produtoService.getProduto(data.idProduto()));
		
		carrinhoItemRepository.save(item);
		
	}

}
