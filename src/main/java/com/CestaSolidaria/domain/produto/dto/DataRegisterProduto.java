package com.CestaSolidaria.domain.produto.dto;

import com.CestaSolidaria.domain.produto.enums.Categoria;

import jakarta.validation.constraints.NotNull;

public record DataRegisterProduto(@NotNull
		                          String nome,
		                          @NotNull
								  String descricao,
								  @NotNull
								  double preco,
								  @NotNull
								  int quantidade,
								  @NotNull
								  String volume,
								  @NotNull
								  Categoria categoria,
								  @NotNull
								  String urlImagem) {}
