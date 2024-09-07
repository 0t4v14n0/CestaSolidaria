package com.CestaSolidaria.domain.user.dependente;

import java.time.LocalDateTime;

import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.dependente.dto.DataRegisterDependente;
import com.CestaSolidaria.domain.user.enums.Situacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "dependentes")
@Entity(name = "Dependentes")
public class Dependente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String nome;
	
	@JoinColumn(name = "data_nascimento")
	private String dataNascimento;	
	
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
	public Dependente () {}
	
	public Dependente (DataRegisterDependente data) {
		this.nome = data.nome();
		this.dataNascimento = data.dataNascimento();
		this.cpf = data.cpf();
		this.criadoEm = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

}
