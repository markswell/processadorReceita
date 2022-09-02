package com.markswell.processadorReceita.model.dto;

import com.markswell.processadorReceita.model.Receita;
import lombok.Builder;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.Objects;

@Data
@Builder
public class ReceitaDTO {

    public static ReceitaDTO of(Receita receita, Boolean resultado) {
        return ReceitaDTO.builder()
                .agencia(receita.getAgencia())
                .conta(receita.getConta())
                .saldo(receita.getSaldo())
                .status(receita.getStatus())
                .resultado(resultado)
                .build();
    }

    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
    private Boolean resultado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceitaDTO that = (ReceitaDTO) o;
        return Objects.equals(agencia, that.agencia) && Objects.equals(conta, that.conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, conta);
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%.2f;%s;%s", agencia, conta, saldo, status, resultado);
    }

}
