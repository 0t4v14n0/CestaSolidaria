package com.CestaSolidaria.domain.user.admin;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CreditoService {
	
    @Scheduled(cron = "0 0 0 1 * ?")
    public void creditDaily() {
        System.out.println("Iniciando processo de credito nas contas - " + LocalDateTime.now());
        creditarContasUsuarios();
        System.out.println("Creditadas... - " + LocalDateTime.now());
    }
    
    private void creditarContasUsuarios() {
    }

}
