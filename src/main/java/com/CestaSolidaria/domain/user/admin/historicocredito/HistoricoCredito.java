package com.CestaSolidaria.domain.user.admin.historicocredito;

import java.time.LocalDateTime;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;
import com.CestaSolidaria.domain.user.admin.historicocredito.enums.TipoMovimentacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "historico_creditos")
@Entity(name = "HistoricoCredito")
public class HistoricoCredito {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private User usuarioId;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	private double valor;
	private String descricao;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
	public HistoricoCredito() {}
	
	public HistoricoCredito(DataHistoricoCredito data) {
		this.usuarioId = data.user();
		this.tipo = data.tipo();
		this.valor = data.valor();
		this.descricao = data.descricao();
		this.criadoEm = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}
	
}
