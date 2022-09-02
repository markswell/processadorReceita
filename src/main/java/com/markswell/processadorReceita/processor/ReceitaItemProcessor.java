package com.markswell.processadorReceita.processor;

import lombok.RequiredArgsConstructor;
import sincronizacaoreceita.ReceitaService;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import com.markswell.processadorReceita.model.Receita;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;

@Component
@RequiredArgsConstructor
public class ReceitaItemProcessor implements ItemProcessor<Receita, ReceitaDTO> {

    private final ReceitaService receitaService;

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
