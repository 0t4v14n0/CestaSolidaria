package com.CestaSolidaria.domain.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.CestaSolidaria.domain.user.admin.dto.DataStatusUser;
import com.CestaSolidaria.domain.user.dto.DataAutenticationUser;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.residencia.Residencia;
import com.CestaSolidaria.domain.user.residencia.ResidenciaService;
import com.CestaSolidaria.infra.security.TokenDataJWT;
import com.CestaSolidaria.infra.security.TokenService;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ResidenciaService residenciaService;
	
	public ResponseEntity<DataDeteilsUser> registerUsuario(DataRegisterUser data,
								UriComponentsBuilder uriBuilder) {
    	var user = new User(data);
    	user.setSenha(passwordCrypt(data.senha()));
    	userRepository.save(user);
    	Residencia residencia = new Residencia(data.residencia(),user);
    	residenciaService.saveResidencia(residencia);
    	user.setResidencia(residencia);
    	userRepository.save(user);
    	var uri = uriBuilder.path("").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new DataDeteilsUser(user));
	}
	
	public ResponseEntity<TokenDataJWT> login (DataAutenticationUser data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var authentication = manager.authenticate(authenticationToken);   
        var tokenJWT = tokenService.gerarToken((User)authentication.getPrincipal());  
        return ResponseEntity.ok(new TokenDataJWT(tokenJWT)); 
	}
	
	public DataStatusUser atualizarUser(@Valid DataRegisterUser data, String cpf) {
		
		User user = buscaUsuario(cpf);

		if(data.nome() != null && !data.nome().isEmpty())user.setNome(data.nome());
		if(data.cpf() != null && !data.cpf().isEmpty())user.setCpf(data.cpf());
		if(data.senha() != null && !data.senha().isEmpty())user.setSenha(data.senha());
		if(data.telefone() != null && !data.telefone().isEmpty())user.setTelefone(data.telefone());
		if(data.situacao() != null)user.setSituacao(data.situacao());
		if(data.residencia() != null)user.setResidencia(residenciaService.atualiza(data.residencia(),user.getResidencia()));
		
		return new DataStatusUser(user);
	}
	
    private String passwordCrypt(String password) {
    	String psswordEncrypted = passwordEncoder.encode(password);
		return psswordEncrypted;
    }

	public User buscaUsuario(String cpf) {
		return (User)userRepository.findByCpf(cpf);
	}

	public List<User> findByStatus(Status status) {
		return userRepository.findByStatus(status, null);		
	}

	public void userRepositorySave(User user) {
		userRepository.save(user);
	}

}
