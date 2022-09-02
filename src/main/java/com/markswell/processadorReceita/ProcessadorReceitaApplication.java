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

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProcessadorReceitaApplication.class, args);

	}

	@Bean("receitaStep")
	public Step buildAircraftStep(ReceitaFlatFileItemReader aircraftItemReader, ReceitaItemProcessor aircraftItemProcessor, ReceitaWriter aircraftItemWriter) {
		return stepBuilderFactory.get("aircraftStep")
				.<Receita, ReceitaDTO>chunk(4)
				.reader(aircraftItemReader)
				.processor(aircraftItemProcessor)
				.writer(aircraftItemWriter)
				.build();
	}

	@Bean("receitaJob")
	public Job buildAircraftJob(final Step receitaStep) {
		return jobBuilderFactory.get("aircraftLoadingJob")
				.incrementer(new RunIdIncrementer())
				.flow(receitaStep)
				.end()
				.build();
	}
}
