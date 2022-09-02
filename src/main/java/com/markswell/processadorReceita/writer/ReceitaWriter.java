package com.markswell.processadorReceita.writer;

import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceitaWriter implements ItemWriter<ReceitaDTO> {

    @Override
    public void write(List<? extends ReceitaDTO> list) throws Exception {
        list.forEach(e -> System.out.println(e.toString()));
    }
}
