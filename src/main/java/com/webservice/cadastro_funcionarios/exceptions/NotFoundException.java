package com.webservice.cadastro_funcionarios.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
