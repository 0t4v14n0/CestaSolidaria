package com.CestaSolidaria.domain.carrinho;

import java.time.LocalDateTime;
import java.util.List;

import com.CestaSolidaria.domain.carrinho.enums.StatusCarrinho;
import com.CestaSolidaria.domain.carrinho.item.CarrinhoItem;
import com.CestaSolidaria.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "carrinho")
@Entity(name = "Carrinho")
public class Carrinho {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private User usuarioId;
	
	private double total;
	
	@Enumerated(EnumType.STRING)
	private StatusCarrinho statusCarrinho;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
	private List<CarrinhoItem> itens;
		
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public StatusCarrinho getStatusCarrinho() {
		return statusCarrinho;
	}

	public void setStatusCarrinho(StatusCarrinho statusCarrinho) {
		this.statusCarrinho = statusCarrinho;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

}
