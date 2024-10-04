package com.CestaSolidaria.domain.user;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.CestaSolidaria.domain.user.dependente.Dependente;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;
import com.CestaSolidaria.domain.user.enums.Situacao;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.enums.TipoBeneficio;
import com.CestaSolidaria.domain.user.residencia.Residencia;
import com.CestaSolidaria.domain.user.role.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "usuarios")
@Entity(name = "User")
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cpf;
	private String senha;
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	private TipoBeneficio tipoBeneficio;
	
	@JoinColumn(name = "data_nascimento")
	private String dataNascimento;
	
	@JoinColumn(name = "renda_total")
	private double rendaTotal;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    
    private double creditos;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
	
	@JoinColumn(name = "criado_em")
	private LocalDateTime criadoEm;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residencia_id", referencedColumnName = "id")
    private Residencia residencia;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dependente> dependentes;
	
	public User() {}
	
	public User(DataRegisterUser dataRegisterUser, Role defaultRole) {
		this.nome = dataRegisterUser.nome();
		this.senha = dataRegisterUser.senha();
		this.cpf = dataRegisterUser.cpf();
		this.telefone = dataRegisterUser.telefone();
		this.dataNascimento = dataRegisterUser.dataNascimento();
		this.rendaTotal = dataRegisterUser.rendaTotal();
		this.roles = new HashSet<>();
	    this.roles.add(defaultRole);
		this.status = Status.PENDENTE;
		this.situacao = dataRegisterUser.situacao();
		this.creditos = 0;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoBeneficio getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(TipoBeneficio tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getRendaTotal() {
		return rendaTotal;
	}

	public void setRendaTotal(double rendaTotal) {
		this.rendaTotal = rendaTotal;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());    }
	
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return cpf;
	}

}
