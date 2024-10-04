package com.CestaSolidaria.domain.produto;

import java.time.LocalDateTime;

import com.CestaSolidaria.domain.produto.categoria.Categoria;
import com.CestaSolidaria.domain.produto.dto.DataRegisterProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "produtos")
@Entity(name = "Produto")
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private double preco;
	private int quantidade;
	private String volume;
	
	@ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
	
	@JoinColumn(name = "url_imagem")
	private String urlImagem;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
	public Produto() {}
	
	public Produto(DataRegisterProduto data) {
		this.nome = data.nome();
		this.descricao = data.descricao();
		this.preco = data.preco();
		this.quantidade = data.quantidade();
		this.volume = data.volume();
		this.urlImagem = data.urlImagem();
		this.criadoEm = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getUrlImagem() {
		return urlImagem;
	}
	
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
	public void setCriadoEm(LocalDateTime criado_em) {
		this.criadoEm = criado_em;
	}

}
