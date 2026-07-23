package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record FuncionarioRecordDto(
    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    String Nome,

    @NotBlank(message = "O email deve ser informado")
    @Size(min = 5, max = 100, message = "O email deve ter entre 5 e 100 caracteres")
    @Email(message = "O email deve ser válido")
    String Email,

    @NotBlank(message = "O documento deve ser informado")
    @Size(min = 5, max = 20, message = "O documento deve ter entre 5 e 20 caracteres")
    String Documento,

    @NotNull(message = "A data de nascimento deve ser informada")
    @Past(message = "A data de nascimento deve ser anterior a data atual")
    LocalDate DataNascimento,

    @NotNull(message = "O status deve ser informado")
    Boolean Status,

    @NotNull(message = "O salário deve ser informado")
    @DecimalMin(value = "1.0", message = "O salário deve ser maior ou igual a 1.0")
    Float Salario,

    @NotNull(message = "O cargo deve ser informado")
    String Cargo) { }
