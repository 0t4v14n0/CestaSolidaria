package com.CestaSolidaria.domain.user.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.produto.ProdutoService;
import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserService;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.enums.TipoBeneficio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CreditoService {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UserService userService;
	
    @Scheduled(cron = "0 0 0 1 * ?")
    public void creditDaily() {
    	
        System.out.println("Iniciando processo de credito nas contas - " + LocalDateTime.now());
        creditarContasUsuarios();
        System.out.println("Creditadas... - " + LocalDateTime.now());    
    }
    
    private void creditarContasUsuarios() {
    	
    	List<User> users = userService.findByStatus(Status.APROVADO);
    	
    	if(users.isEmpty()) {
    		new EntityNotFoundException("Usuario não encontrado");
    	}
    	
    	List<Double> lista = calcValorPorTipo();
    	
        users.forEach(user -> {
            double valorCredito = 0;
            switch (user.getTipoBeneficio()) {
                case TIPO1 -> valorCredito = lista.get(0);
                case TIPO2 -> valorCredito = lista.get(1);
                case TIPO3 -> valorCredito = lista.get(2);
            }
            user.setCreditos(valorCredito);
            userService.userRepositorySave(user);
        });
    }
    
    private List<Double> calcValorPorTipo() {
    	
    	double totalPrecoEstoque = produtoService.valorTotalMercadoria();
    	
    	List<User> users = userService.findByStatus(Status.APROVADO);
    	
        Map<TipoBeneficio, Long> quantidadePorBeneficio = users.stream()
                .collect(Collectors.groupingBy(User::getTipoBeneficio, Collectors.counting()));
        
        long totalBeneficiariosTipo1 = quantidadePorBeneficio.getOrDefault(TipoBeneficio.TIPO1, 0L);
        long totalBeneficiariosTipo2 = quantidadePorBeneficio.getOrDefault(TipoBeneficio.TIPO2, 0L);
        long totalBeneficiariosTipo3 = quantidadePorBeneficio.getOrDefault(TipoBeneficio.TIPO3, 0L);
        
        int pesoTipo1 = 3;
        int pesoTipo2 = 2;
        int pesoTipo3 = 1;

        long totalPesos = (totalBeneficiariosTipo1 * pesoTipo1) +
                          (totalBeneficiariosTipo2 * pesoTipo2) +
                          (totalBeneficiariosTipo3 * pesoTipo3) ;

        double valorPorTipo1 = (totalPrecoEstoque * (totalBeneficiariosTipo1 * pesoTipo1)) / totalPesos;
        double valorPorTipo2 = (totalPrecoEstoque * (totalBeneficiariosTipo2 * pesoTipo2)) / totalPesos;
        double valorPorTipo3 = (totalPrecoEstoque * (totalBeneficiariosTipo3 * pesoTipo3)) / totalPesos;
        
        List<Double> lista = new ArrayList<>();
        lista.add(valorPorTipo1);
        lista.add(valorPorTipo2);
        lista.add(valorPorTipo3);
        
        return lista;
    }
    
    

}
