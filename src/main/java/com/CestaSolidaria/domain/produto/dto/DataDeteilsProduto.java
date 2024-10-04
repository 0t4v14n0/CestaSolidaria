package com.CestaSolidaria.domain.produto.dto;

import com.CestaSolidaria.domain.produto.Produto;
import com.CestaSolidaria.domain.produto.categoria.dto.DataCategoria;

public record DataDeteilsProduto(Long id,
								 String nome,
		  						 String descricao,
		  						 double preco,
		  						 int quantidade,
		  						 String volume,
		  						 DataCategoria categoria,
		  						 String urlImagem) {

	public DataDeteilsProduto(Produto produto) {
		this(produto.getId(),
			 produto.getNome(),
			 produto.getDescricao(),
			 produto.getPreco(),
			 produto.getQuantidade(),
			 produto.getVolume(),
			 new DataCategoria(produto.getCategoria()),
			 produto.getUrlImagem());
	}

}
