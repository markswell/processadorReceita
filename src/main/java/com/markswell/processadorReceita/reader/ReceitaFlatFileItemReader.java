package com.markswell.processadorReceita.reader;

import com.markswell.processadorReceita.model.Receita;
import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.item.file.FlatFileItemReader;
import com.markswell.processadorReceita.mapper.ReceitaFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class ReceitaFlatFileItemReader extends FlatFileItemReader<Receita> {

    public ReceitaFlatFileItemReader(String path) {
        DefaultLineMapper<Receita> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer(";"));
        defaultLineMapper.setFieldSetMapper(new ReceitaFieldSetMapper());
        this.setLineMapper(defaultLineMapper);
        this.setResource(new FileSystemResource(path));
    }

}
