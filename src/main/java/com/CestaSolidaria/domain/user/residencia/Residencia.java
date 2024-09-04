package com.CestaSolidaria.domain.user.residencia;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.residencia.dto.DataRegisterResidencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "residencia")
@Entity(name = "Residencia")
public class Residencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne(mappedBy = "residencia")
    private User usuarioId;
	
	private String endereco;
	private String cidade;
	private String estado;
	private String cep;
	private String pais;
	private String referencia;
	
	public Residencia() {}
	
	public Residencia(DataRegisterResidencia dataRegisterResidencia) {
		this.endereco = dataRegisterResidencia.endereco();
		this.cidade = dataRegisterResidencia.cidade();
		this.estado = dataRegisterResidencia.estado();
		this.cep = dataRegisterResidencia.cep();
		this.pais = dataRegisterResidencia.pais();
		this.referencia = dataRegisterResidencia.referencia();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
}
