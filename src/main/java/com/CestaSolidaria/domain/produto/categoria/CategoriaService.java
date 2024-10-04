package com.CestaSolidaria.domain.produto.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findCategoria(String nome) {
		
		Categoria categoria = categoriaRepository.findByName(nome).get();
		if(categoria == null) new EntityNotFoundException("Categoria nao encontrada !");	
		return categoria;
	}

}
