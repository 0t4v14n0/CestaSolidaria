package com.CestaSolidaria.domain.produto.dto;

import com.CestaSolidaria.domain.produto.enums.Categoria;

public record DataRegisterProduto(String nome,
								  String descricao,
								  double preco,
								  int quantidade,
								  String volume,
								  Categoria categoria,
								  String urlImagem) {}
