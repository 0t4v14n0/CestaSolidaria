package com.CestaSolidaria.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.CestaSolidaria.domain.user.dto.DataAutenticationUser;
import com.CestaSolidaria.domain.user.dto.DataDeteilsUser;
import com.CestaSolidaria.domain.user.dto.DataRegisterUser;
import com.CestaSolidaria.infra.security.TokenDataJWT;
import com.CestaSolidaria.infra.security.TokenService;

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
	
	public ResponseEntity<DataDeteilsUser> registerUsuario(DataRegisterUser data,
								UriComponentsBuilder uriBuilder) {
    	var user = new User(data);
    	user.setSenha(passwordCrypt(data.senha()));   	
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
	
    public String passwordCrypt(String password) {
    	String psswordEncrypted = passwordEncoder.encode(password);
		return psswordEncrypted;
    }

	public User buscaUsuario(String name) {
		return (User)userRepository.findByCpf(name);
	}

}
