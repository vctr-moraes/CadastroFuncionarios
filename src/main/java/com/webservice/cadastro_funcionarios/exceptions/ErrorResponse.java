package com.webservice.cadastro_funcionarios.exceptions;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(int codigoErro, String mensagemErro, Map<String, String> detalhesErro) { }
