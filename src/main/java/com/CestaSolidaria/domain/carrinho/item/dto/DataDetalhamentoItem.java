package com.CestaSolidaria.domain.carrinho.item.dto;

import com.CestaSolidaria.domain.carrinho.item.CarrinhoItem;
import com.CestaSolidaria.domain.produto.dto.DataDeteilsProduto;

public record DataDetalhamentoItem(DataDeteilsProduto produto,
								   int quantidade) {
	
	public DataDetalhamentoItem(CarrinhoItem item) {
		this(new DataDeteilsProduto(item.getProduto()),item.getQuantidade());
	}

}
