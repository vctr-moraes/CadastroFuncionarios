package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

public record FuncionarioDto(UUID id,
                             @NotBlank String nome,
                             @NotBlank String email,
                             @NotBlank String documento,
                             LocalDate dataNascimento,
                             Boolean status,
                             Float salario) { }
