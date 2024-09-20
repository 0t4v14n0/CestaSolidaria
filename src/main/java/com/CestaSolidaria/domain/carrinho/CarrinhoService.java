package com.CestaSolidaria.domain.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.carrinho.enums.StatusCarrinho;
import com.CestaSolidaria.domain.user.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private UserService userService;
	
	public Carrinho getCarrinhoAbertoUser (String cpf) {
		
		Carrinho carrinho = carrinhoRepository.findByUserId(userService.buscaUsuario(cpf));
		
		if (carrinho == null || carrinho.getStatusCarrinho() != StatusCarrinho.ABERTO) {
			new EntityNotFoundException("");
		}	
		return carrinho;
	}

	public void save(Carrinho carrinho) {
		carrinhoRepository.save(carrinho);
	}

}
