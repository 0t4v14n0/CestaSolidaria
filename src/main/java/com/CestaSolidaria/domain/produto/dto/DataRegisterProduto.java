package com.CestaSolidaria.domain.produto.dto;

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
								  String categoria,
								  @NotNull
								  String urlImagem) {}
