package com.markswell.processadorReceita.mapper;

import com.markswell.processadorReceita.LineException;
import com.markswell.processadorReceita.model.Receita;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import static java.lang.Double.parseDouble;

public class ReceitaFieldSetMapper implements FieldSetMapper<Receita> {

    @Override
    public Receita mapFieldSet(FieldSet fieldSet) throws BindException {
        try{
            return Receita.builder()
                    .agencia(fieldSet.readString(0))
                    .conta(fieldSet.readString(1))
                    .saldo(parseDouble(getDouble(fieldSet.readString(2))))
                    .status(fieldSet.readString(3))
                    .build();
        } catch (Exception e) {
            throw new LineException("Exception on binding");
        }
    }

    private static String getDouble(String value) {
        return value.replace(",", ".");
    }
}
