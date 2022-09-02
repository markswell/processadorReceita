package com.markswell.processadorReceita.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import sincronizacaoreceita.ReceitaService;
import org.springframework.context.annotation.Bean;
import com.markswell.processadorReceita.model.Receita;
import org.springframework.context.annotation.Configuration;
import com.markswell.processadorReceita.writer.ReceitaWriter;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import com.markswell.processadorReceita.skippolice.ReceitaSkipPolicy;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import com.markswell.processadorReceita.processor.ReceitaItemProcessor;
import com.markswell.processadorReceita.reader.ReceitaFlatFileItemReader;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

@Configuration
@RequiredArgsConstructor
public class ReceitaConfig {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean("receitaStep")
	public Step buildReceitaStep(ReceitaFlatFileItemReader receitaItemReader, ReceitaItemProcessor receitaItemProcessor, ReceitaWriter receitaItemWriter) {
		return stepBuilderFactory.get("receitaStep")
				.<Receita, ReceitaDTO>chunk(1000)
				.reader(receitaItemReader)
				.processor(receitaItemProcessor)
				.writer(receitaItemWriter)
                .faultTolerant()
                .skipPolicy(new ReceitaSkipPolicy())
                .build();
	}

    @Bean("receitaJob")
    public Job buildReceitaJob(final Step receitaStep) {
        return jobBuilderFactory.get("receitaJob")
                .incrementer(new RunIdIncrementer())
                .flow(receitaStep)
                .end()
                .build();
    }

    @Bean
    public ReceitaService receitaService() {
        return new ReceitaService();
    }

    @Bean
    public ReceitaItemProcessor receitaItemProcessor() {
        return new ReceitaItemProcessor();
    }

}
