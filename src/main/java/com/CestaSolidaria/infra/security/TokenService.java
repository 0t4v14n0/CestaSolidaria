package com.CestaSolidaria.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
	
	@Value("${secret}")
	private String secret;
		
	    public String gerarToken(User user) {
	    	
	        try {
	            var algoritmo = Algorithm.HMAC256(secret);
	            return JWT.create()
	                .withIssuer("API CestaSolidaria")
	                .withSubject(user.getCpf())
	                .withClaim("id", user.getId())
	                .withExpiresAt(dataExpiracao())
	                .sign(algoritmo);
	        } catch (JWTCreationException exception){
	            throw new RuntimeException("erro ao gerrar token jwt", exception);
	        }		
	    }
	    
	    public String getSubject(String tokenJWT) {
	        try {
	            var algoritmo = Algorithm.HMAC256(secret);
	            return JWT.require(algoritmo)
	                            .withIssuer("API CestaSolidaria")
	                            .build()
	                            .verify(tokenJWT)
	                            .getSubject();
	        } catch (JWTVerificationException exception) {
	            throw new RuntimeException("Token JWT inválido ou expirado!");
	        }
	    }

	    private Instant dataExpiracao() {
	        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	    }
	    
}
