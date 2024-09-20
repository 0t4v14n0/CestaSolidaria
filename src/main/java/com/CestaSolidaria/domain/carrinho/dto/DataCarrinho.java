package com.CestaSolidaria.domain.carrinho.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.CestaSolidaria.domain.carrinho.Carrinho;
import com.CestaSolidaria.domain.carrinho.enums.StatusCarrinho;
import com.CestaSolidaria.domain.carrinho.item.dto.DataDetalhamentoItem;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;

public record DataCarrinho(DataDeteilsUser usuarioId,
						   StatusCarrinho statusCarrinho,
						   List<DataDetalhamentoItem> itens,
						   double total,
						   LocalDateTime criadoEm) {
	
	public DataCarrinho (Carrinho carrinho) {
		this(new DataDeteilsUser(carrinho.getUsuarioId()),
				carrinho.getStatusCarrinho(),
				carrinho.getItens().stream()
								   .map(DataDetalhamentoItem::new)
								   .collect(Collectors.toList()),
				carrinho.getTotal(),
				carrinho.getCriadoEm());
	}
}
