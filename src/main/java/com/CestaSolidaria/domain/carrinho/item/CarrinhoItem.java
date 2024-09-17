package com.CestaSolidaria.domain.carrinho.item;

import java.time.LocalDateTime;

import com.CestaSolidaria.domain.carrinho.Carrinho;
import com.CestaSolidaria.domain.produto.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "carrinho_item")
@Entity(name = "CarrinhoItem")
public class CarrinhoItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "carrinho_id")
	private Carrinho carrinhoId;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produtoId;
	
	private int quantidade;
	
	private double preco;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
	@JoinColumn(name = "atualizado_em")
	private LocalDateTime atualizadoEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinhoId() {
		return carrinhoId;
	}

	public void setCarrinhoId(Carrinho carrinhoId) {
		this.carrinhoId = carrinhoId;
	}

	public Produto getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Produto produtoId) {
		this.produtoId = produtoId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

}
