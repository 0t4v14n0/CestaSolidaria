package com.CestaSolidaria.domain.user.admin.historicocredito.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.admin.historicocredito.HistoricoCredito;
import com.CestaSolidaria.domain.user.admin.historicocredito.enums.TipoMovimentacao;

public record DataHistoricoCredito(User user,
								   TipoMovimentacao tipo,
								   double valor,
								   String descricao) {

	public static List<DataHistoricoCredito> fromPage(Page<HistoricoCredito> historicoCreditoPage) {
        return historicoCreditoPage.stream()
                                   .map(h -> new DataHistoricoCredito(
                                       h.getUsuarioId(),
                                       h.getTipo(),
                                       h.getValor(),
                                       h.getDescricao()
                                   ))
                                   .toList();
    }
}
