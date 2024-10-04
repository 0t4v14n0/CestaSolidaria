package com.CestaSolidaria.domain.produto.categoria.dto;

import com.CestaSolidaria.domain.produto.categoria.Categoria;

public record DataCategoria(Long id,
							String categoria) {
	
	public DataCategoria(Categoria categoria) {
		this(categoria.getId(), categoria.getName());
	}

}
