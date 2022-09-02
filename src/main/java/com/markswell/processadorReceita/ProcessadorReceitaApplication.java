package com.markswell.processadorReceita;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import com.markswell.processadorReceita.model.Receita;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import com.markswell.processadorReceita.writer.ReceitaWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import com.markswell.processadorReceita.processor.ReceitaItemProcessor;
import com.markswell.processadorReceita.reader.ReceitaFlatFileItemReader;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

@EnableBatchProcessing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProcessadorReceitaApplication {

	private static String path;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		path = args[0];
		SpringApplication.run(ProcessadorReceitaApplication.class, args);
	}

	@Bean("receitaStep")
	public Step buildReceitaStep(ReceitaFlatFileItemReader receitaItemReader, ReceitaItemProcessor receitaItemProcessor, ReceitaWriter receitaItemWriter) {
		return stepBuilderFactory.get("receitaStep")
				.<Receita, ReceitaDTO>chunk(1000)
				.reader(receitaItemReader)
				.processor(receitaItemProcessor)
				.writer(receitaItemWriter)
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
	public ReceitaFlatFileItemReader receitaFlatFileItemReader() {
		ReceitaFlatFileItemReader receitaFlatFileItemReader = new ReceitaFlatFileItemReader(path);
		receitaFlatFileItemReader.setLinesToSkip(1);
		return receitaFlatFileItemReader;
	}

	@Bean
	public ReceitaWriter receitaWriter() {
		return new ReceitaWriter(path);
	}

}
