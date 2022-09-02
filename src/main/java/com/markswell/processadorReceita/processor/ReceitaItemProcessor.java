package com.markswell.processadorReceita.processor;

import sincronizacaoreceita.ReceitaService;
import org.springframework.batch.item.ItemProcessor;
import com.markswell.processadorReceita.model.Receita;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceitaItemProcessor implements ItemProcessor<Receita, ReceitaDTO> {

    @Autowired
    private ReceitaService receitaService;

    @Override
    public ReceitaDTO process(Receita receita) throws Exception {
        try {
            boolean atualizarConta = receitaService.atualizarConta(receita.getAgencia(), receita.getConta().replace("-", ""), receita.getSaldo(), receita.getStatus());
            return ReceitaDTO.of(receita, atualizarConta);
        } catch (Exception e) {
            return ReceitaDTO.of(receita, false);
        }

    }
}
