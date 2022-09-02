package com.markswell.processadorReceita.config;

import sincronizacaoreceita.ReceitaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceitaConfig {

    @Bean
    public ReceitaService receitaService() {
        return new ReceitaService();
    }
}
