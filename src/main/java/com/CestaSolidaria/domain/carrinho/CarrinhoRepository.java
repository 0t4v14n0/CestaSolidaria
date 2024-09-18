package com.CestaSolidaria.domain.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CestaSolidaria.domain.user.User;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long>{

	Carrinho findByUserId(User user);

}
