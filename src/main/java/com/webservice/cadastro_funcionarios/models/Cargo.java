package com.webservice.cadastro_funcionarios.models;

public enum Cargo {
    ESTAGIARIO(0),
    ANALISTA(1),
    GERENTE(2),
    DIRETOR(3);

    private final int value;

    private Cargo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
