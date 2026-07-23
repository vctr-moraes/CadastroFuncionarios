package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.*;

public record EnderecoRecordDto(
    @NotBlank(message = "O logradouro deve ser informado")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres")
    String Logradouro,

    @NotBlank(message = "O bairro deve ser informado")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre 3 e 100 caracteres")
    String Bairro,

    @NotBlank(message = "O complemento deve ser informado")
    @Size(min = 3, max = 100, message = "O complemento deve ter entre 3 e 100 caracteres")
    String Complemento,

    @NotBlank(message = "O cidade deve ser informado")
    @Size(min = 3, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    String Cidade,

    @NotBlank(message = "O estado deve ser informado")
    @Size(min = 2, max = 50, message = "O estado deve ter entre 2 e 50 caracteres")
    String Estado,

    @NotBlank(message = "O país deve ser informado")
    @Size(min = 2, max = 50, message = "O país deve ter entre 2 e 50 caracteres")
    String Pais,

    @NotBlank(message = "O cep deve ser informado")
    @Size(min = 8, max = 10, message = "O cep deve ter entre 8 e 10 caracteres")
    String Cep) { }
