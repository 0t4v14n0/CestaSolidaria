package com.CestaSolidaria.domain.user.dependente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.user.UserService;
import com.CestaSolidaria.domain.user.dependente.dto.DataAtualizarDependente;
import com.CestaSolidaria.domain.user.dependente.dto.DataDeteilsDependente;
import com.CestaSolidaria.domain.user.dependente.dto.DataRegisterDependente;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DependenteService {
	
	@Autowired
	private DependenteRepository dependenteRepository;
	
	@Autowired
	private UserService userService;
	
	public DataDeteilsDependente addDependente(DataRegisterDependente data, String nome) {
		
		Dependente dep = new Dependente(data,userService.buscaUsuario(nome));
		dependenteRepository.save(dep);
		
		return new DataDeteilsDependente(dep);
	}
	
	public DataDeteilsDependente atualizarDependente(DataAtualizarDependente data, String cpf) {
		
		Dependente dep = findDependenteByUser(data.id(), cpf);
		
    	if(dep == null) {
    		new EntityNotFoundException("Dependente não encontrado");
    	}
		
		if(data.nome() != null && !data.nome().isEmpty()) dep.setNome(data.nome());
		if(data.cpf() != null && !data.cpf().isEmpty()) dep.setCpf(data.cpf());
		if(data.dataNascimento() != null && !data.dataNascimento().isEmpty()) dep.setDataNascimento(data.dataNascimento());
		if(data.situacao() != null) dep.setSituacao(data.situacao());
		
		return new DataDeteilsDependente(dep);
	}
	
	public List<DataDeteilsDependente> todosDependentes(String cpf) {
		
		List<Dependente> dependentes = dependenteRepository.findByIdAndUserId(userService.buscaUsuario(cpf).getId());
		
    	if(dependentes.isEmpty()) {
    		new EntityNotFoundException("Dependentes não encontrado");
    	}
    	
		List<DataDeteilsDependente> detalhesDependentes = dependentes.stream()
		        .map(dependente -> new DataDeteilsDependente(dependente))
		        .collect(Collectors.toList());
		
		return detalhesDependentes;
	}
	
	public void deleteDependente(Long id, String cpf) {
		Dependente dep = findDependenteByUser(id, cpf);
		dependenteRepository.delete(dep);
	}
	
	public DataDeteilsDependente dependentesPorId(Long id, String name) {
		return new DataDeteilsDependente(findDependenteByUser(id,name));
	}
	
	private Dependente findDependenteByUser(Long id, String cpf) {
		return dependenteRepository.findDependenteByUserId(userService.buscaUsuario(cpf).getId(), id);
	}
	
}
