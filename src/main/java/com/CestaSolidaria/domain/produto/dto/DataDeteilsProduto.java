package com.CestaSolidaria.domain.produto.dto;

import com.CestaSolidaria.domain.produto.Produto;
import com.CestaSolidaria.domain.produto.enums.Categoria;

public record DataDeteilsProduto(Long id,
								 String nome,
		  						 String descricao,
		  						 double preco,
		  						 int quantidade,
		  						 String volume,
		  						 Categoria categoria,
		  						 String urlImagem) {

	public DataDeteilsProduto(Produto produto) {
		this(produto.getId(),
			 produto.getNome(),
			 produto.getDescricao(),
			 produto.getPreco(),
			 produto.getQuantidade(),
			 produto.getVolume(),
			 produto.getCategoria(),
			 produto.getUrlImagem());
	}

}
