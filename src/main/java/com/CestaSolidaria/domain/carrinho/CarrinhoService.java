package com.CestaSolidaria.domain.carrinho;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.carrinho.dto.DataCarrinho;
import com.CestaSolidaria.domain.carrinho.enums.StatusCarrinho;
import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private UserService userService;
	
	public Carrinho getCarrinhoAbertoUser (String cpf) {
		
		User user = userService.buscaUsuario(cpf);
		
		Carrinho carrinho = carrinhoRepository.findByUserIdAndStatusCarrinho(user.getId(),StatusCarrinho.ABERTO);
		
		if (carrinho == null){
			Carrinho carrinhoNovo = new Carrinho();
			carrinhoNovo.setUsuarioId(user);
			carrinhoNovo.setStatusCarrinho(StatusCarrinho.ABERTO);
			carrinhoNovo.setCriadoEm(LocalDateTime.now());
			return carrinhoNovo;
		}
		return carrinho;
	}
	
	public DataCarrinho finalizarCarrinho(String cpf) {
		
		Carrinho carrinho = getCarrinhoAbertoUser(cpf);
		
		User user = userService.buscaUsuario(cpf);
		
		double total = valorTotalCarrinho(carrinho);
		
		if(user.getCreditos() < total) {
			new EntityNotFoundException("Saldo em credito excedido !");
		}
		
		user.setCreditos(user.getCreditos() - total);
		carrinho.setStatusCarrinho(StatusCarrinho.FECHADO);
		return new DataCarrinho(carrinho);
	}
	
	public double valorTotalCarrinho(Carrinho c) {
				
	    double total = c.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
	    
		return total;
	}
	
	public void deletarCarrinho(String cpf) {
		
		Carrinho carrinho = getCarrinhoAbertoUser(cpf);
		
		carrinho.setStatusCarrinho(StatusCarrinho.CANCELADO);
		
		save(carrinho);
		
	}
	
	public void save(Carrinho carrinho) {
		carrinhoRepository.save(carrinho);
	}

}
