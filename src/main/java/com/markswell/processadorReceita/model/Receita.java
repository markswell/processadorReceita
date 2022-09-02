package com.markswell.processadorReceita.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Receita implements Serializable {

    private static final long serialVersionUID = 1485464403699128373L;

    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
}
