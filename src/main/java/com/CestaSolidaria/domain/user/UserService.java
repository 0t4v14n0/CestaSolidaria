package com.CestaSolidaria.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    public String passwordCrypt(String password) {
    	String psswordEncrypted = passwordEncoder.encode(password);
		return psswordEncrypted;
    }

}
