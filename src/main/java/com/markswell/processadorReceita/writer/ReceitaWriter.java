package com.markswell.processadorReceita.writer;

import com.markswell.processadorReceita.exception.LineException;
import org.springframework.batch.item.ItemWriter;
import com.markswell.processadorReceita.model.dto.ReceitaDTO;

import java.io.*;
import java.util.List;

public class ReceitaWriter implements ItemWriter<ReceitaDTO> {

    private final String path;

    public ReceitaWriter(String path) {
        this.path = path.replace(".csv", "_return.csv");
    }

    @Override
    public void write(List<? extends ReceitaDTO> list) throws Exception {
        File file = new File(path);
        if(!file.exists()) {
            createFile(file);
        }
        writeReceitaOutput(list, file);
    }

    private void writeReceitaOutput(List<? extends ReceitaDTO> list, File file) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)){

            list.forEach(line -> out.println(line.toString()));

        }  catch (IOException e) {
            throw new LineException(e.getMessage());
        }
    }

    private void createFile(File file) throws IOException {
        file.createNewFile();
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("agencia;conta;saldo;status;resultado");
        }
    }

}
