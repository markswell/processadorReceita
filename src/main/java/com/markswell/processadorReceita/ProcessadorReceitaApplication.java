package com.markswell.processadorReceita;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import com.markswell.processadorReceita.writer.ReceitaWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.markswell.processadorReceita.processor.ReceitaItemProcessor;
import com.markswell.processadorReceita.reader.ReceitaFlatFileItemReader;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;


@EnableBatchProcessing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProcessadorReceitaApplication {

	private static String path;

	public static void main(String[] args) {
		path = args[0];
		SpringApplication.run(ProcessadorReceitaApplication.class, args);
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
