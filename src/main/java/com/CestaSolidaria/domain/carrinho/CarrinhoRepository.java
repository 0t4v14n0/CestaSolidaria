package com.CestaSolidaria.domain.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.CestaSolidaria.domain.carrinho.enums.StatusCarrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho,Long>{

	@Query("SELECT c FROM Carrinho c WHERE c.user.id = :usuario_id "
								  + "AND c.statusCarrinho = :status")
	Carrinho findByUserIdAndStatusCarrinho(@Param("usuario_id") Long userId,
										   @Param("status") StatusCarrinho status);
}
