package com.CestaSolidaria.domain.produto.dto;

public record DataUpdatedProduto(String nome,
		  						 String descricao,
		  						 double preco,
		  						 int quantidade,
		  						 String volume,
		  						 String categoria,
		  						 String urlImagem) {}
