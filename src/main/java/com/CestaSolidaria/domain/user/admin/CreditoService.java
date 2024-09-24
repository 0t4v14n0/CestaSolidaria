package com.CestaSolidaria.domain.user.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.CestaSolidaria.domain.produto.ProdutoService;
import com.CestaSolidaria.domain.user.User;
import com.CestaSolidaria.domain.user.UserService;
import com.CestaSolidaria.domain.user.admin.historicocredito.HistoricoCreditoService;
import com.CestaSolidaria.domain.user.admin.historicocredito.dto.DataHistoricoCredito;
import com.CestaSolidaria.domain.user.admin.historicocredito.enums.TipoMovimentacao;
import com.CestaSolidaria.domain.user.enums.Status;
import com.CestaSolidaria.domain.user.enums.TipoBeneficio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CreditoService {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HistoricoCreditoService historicoCreditoService;
	
    @Scheduled(cron = "0 0 0 1 * ?")
    public void creditDaily() {
    	
        System.out.println("Iniciando processo de credito nas contas - " + LocalDateTime.now());
        creditarContasUsuarios();
        System.out.println("Creditadas... - " + LocalDateTime.now());    
    }
    
    private void creditarContasUsuarios() {
    	
    	Page<User> users = userService.findByStatus(Status.APROVADO);
    	
    	if(users.isEmpty()) {
    		new EntityNotFoundException("Usuarios não encontrados");
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
            
            historicoCreditoService.registro(new DataHistoricoCredito(user,
            														  TipoMovimentacao.ADICIONADO,
            														  user.getCreditos(),
            														  "Credito mensal adicionado."));
            userService.userRepositorySave(user);
        });
    }
    
    private List<Double> calcValorPorTipo() {
    	
    	double totalPrecoEstoque = produtoService.valorTotalMercadoria();
    	
    	Page<User> users = userService.findByStatus(Status.APROVADO);
    	
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
        
        double valorMedioTipo1 = valorPorTipo1/totalBeneficiariosTipo1;
        double valorMedioTipo2 = valorPorTipo2/totalBeneficiariosTipo2;
        double valorMedioTipo3 = valorPorTipo3/totalBeneficiariosTipo3;
        
        List<Double> lista = new ArrayList<>();
                
        lista.add((valorMedioTipo1 > 120) ? 120 : valorMedioTipo1);
        lista.add((valorMedioTipo2 > 100) ? 100 : valorMedioTipo2);
        lista.add((valorMedioTipo3 > 80 ) ? 80  : valorMedioTipo3);
        
        return lista;
    }
}
