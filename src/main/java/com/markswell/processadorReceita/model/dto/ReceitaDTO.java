package com.markswell.processadorReceita.model.dto;

import lombok.Data;
import lombok.Builder;
import com.markswell.processadorReceita.model.Receita;

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
                .resultado(resultado ? "processado": "falha")
                .build();
    }

    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
    private String resultado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceitaDTO that = (ReceitaDTO) o;
        return Objects.equals(agencia, that.agencia) && Objects.equals(conta, that.conta);
    }

    @Override
    public int hashCode() {
        int result = agencia.hashCode();
        return 31 * result + conta.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%.2f;%s;%s", agencia, conta, saldo, status, resultado);
    }

}
