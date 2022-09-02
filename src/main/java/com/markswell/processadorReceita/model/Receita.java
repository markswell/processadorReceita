package com.markswell.processadorReceita.model;

import lombok.Data;
import lombok.Builder;

import java.util.Objects;
import java.io.Serializable;

@Data
@Builder
public class Receita implements Serializable {

    private static final long serialVersionUID = 1485464403699128373L;

    private String agencia;
    private String conta;
    private Double saldo;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(agencia, receita.agencia) && Objects.equals(conta, receita.conta);
    }

    @Override
    public int hashCode() {
        int result = agencia.hashCode();
        return 31 * result + conta.hashCode();
    }
}
