package com.markswell.processadorReceita.reader;

import org.springframework.stereotype.Component;
import com.markswell.processadorReceita.model.Receita;
import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.item.file.FlatFileItemReader;
import com.markswell.processadorReceita.mapper.ReceitaFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

@Component
public class ReceitaFlatFileItemReader extends FlatFileItemReader<Receita> {

    public ReceitaFlatFileItemReader() {
        DefaultLineMapper<Receita> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer(";"));
        defaultLineMapper.setFieldSetMapper(new ReceitaFieldSetMapper());
        this.setLineMapper(defaultLineMapper);
        this.setResource(new FileSystemResource("/home/markswell/workspace/Backend/teste.csv"));
    }
}
