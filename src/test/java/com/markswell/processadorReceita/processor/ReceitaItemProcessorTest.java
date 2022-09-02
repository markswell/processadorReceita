package com.markswell.processadorReceita.processor;

import com.markswell.processadorReceita.model.Receita;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sincronizacaoreceita.ReceitaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReceitaItemProcessorTest {

    @Mock
    private ReceitaService receitaService;
    @InjectMocks
    private ReceitaItemProcessor receitaItemProcessor;

    @BeforeEach
    public void init() throws InterruptedException {
        when(receitaService.atualizarConta(any(), any(), anyDouble(), any())).thenReturn(true);
    }

    @Test
    public void processTest() throws Exception {
        Receita receita = Receita.builder().agencia("0001").conta("12345-6").saldo(1256.51).status("A").build();
        ReceitaDTO receitaDTO = receitaItemProcessor.process(receita);
        assertEquals(receita.getSaldo(), receitaDTO.getSaldo());
        assertEquals(receita.getAgencia(), receitaDTO.getAgencia());
        assertEquals(receita.getConta(), receitaDTO.getConta());
        assertEquals(receita.getStatus(), receitaDTO.getStatus());
        assertEquals(true, receitaDTO.getResultado());
    }

}