package com.CestaSolidaria.domain.user.admin.historicocredito.dto;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.admin.historicocredito.enums.TipoMovimentacao;

public record DataHistoricoCredito(User user,
								   TipoMovimentacao tipo,
								   double valor,
								   String descricao) {
}
