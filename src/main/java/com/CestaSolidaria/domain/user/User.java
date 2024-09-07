package com.CestaSolidaria.domain.user;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.CestaSolidaria.domain.user.dependente.Dependente;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;
import com.CestaSolidaria.domain.user.enums.Role;
import com.CestaSolidaria.domain.user.enums.Situacao;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.residencia.Residencia;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Collections;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "User")
public class User implements UserDetails{
	

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String senha;
	private String cpf;
	private String telefone;
	
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    
	private double creditos;
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residencia_id", referencedColumnName = "id")
    private Residencia residencia;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dependente> dependentes;
	
	public User() {}
	
	public User(DataRegisterUser dataRegisterUser) {
		this.nome = dataRegisterUser.nome();
		this.senha = dataRegisterUser.senha();
		this.cpf = dataRegisterUser.cpf();
		this.telefone = dataRegisterUser.telefone();
		this.role = Role.ADMIN;
		this.status = Status.PENDENTE;
		this.situacao = dataRegisterUser.situacao();
		this.creditos = 0;
		this.criadoEm = LocalDateTime.now();
		this.residencia = new Residencia(dataRegisterUser.residencia());
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }
	
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return cpf;
	}

}
