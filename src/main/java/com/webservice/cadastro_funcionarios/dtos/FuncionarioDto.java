package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record FuncionarioDto(@NotBlank String nome,
                             @NotBlank String documento,
                             LocalDate dataNascimento,
                             Boolean status,
                             Float salario) { }
