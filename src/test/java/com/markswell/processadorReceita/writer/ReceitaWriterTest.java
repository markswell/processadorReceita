package com.markswell.processadorReceita.writer;

import com.markswell.processadorReceita.model.dto.ReceitaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ReceitaWriterTest {

    private ReceitaWriter receitaWriter;
    private ResourceLoader resourceLoader;

    @BeforeEach
    public void init() throws IOException {
        resourceLoader = new DefaultResourceLoader();
        String path = resourceLoader.getResource("classpath:test.csv").getFile().getAbsolutePath();
        receitaWriter = new ReceitaWriter(path);
    }

    @Test
    public void writeTest() throws Exception {
        receitaWriter.write(createReceitaDTO());
        File file = resourceLoader.getResource("classpath:test_return.csv").getFile();
        boolean contains = isContains(file, "0001;12345-6;152,61;A;true");
        assertEquals(true, file.exists());
        assertEquals(true, contains);
    }

    private static boolean isContains(File file, String pattern) throws IOException {
        return Files.readAllLines(Paths.get(file.getAbsolutePath()))
                .contains(pattern);
    }

    private List<ReceitaDTO> createReceitaDTO() {
        List<ReceitaDTO> list = new ArrayList<>();
        list.add(ReceitaDTO.builder().agencia("0001").conta("12345-6").saldo(152.61).status("A").resultado(true).build());
        list.add(ReceitaDTO.builder().agencia("0001").conta("12335-7").saldo(120.18).status("A").resultado(true).build());
        list.add(ReceitaDTO.builder().agencia("0001").conta("13345-6").saldo(1458.89).status("A").resultado(true).build());
        list.add(ReceitaDTO.builder().agencia("0001").conta("12345-8").saldo(10586.74).status("I").resultado(true).build());
        return list;
    }
}