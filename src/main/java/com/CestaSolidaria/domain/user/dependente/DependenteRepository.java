package com.CestaSolidaria.domain.user.dependente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {

	@Query("SELECT d FROM Dependente d WHERE d.user.id = :user_id")
	List<Dependente> findByIdAndUserId(@Param("user_id") Long buscaUsuario);
	
	@Query("SELECT d FROM Dependente d WHERE d.user.id = :user_id AND d.id = :id")
	Dependente findDependenteByUserId(@Param("user_id") Long buscaUsuario, @Param("id") Long id);

}
