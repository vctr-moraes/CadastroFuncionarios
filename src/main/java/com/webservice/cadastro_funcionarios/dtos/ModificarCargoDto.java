package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ModificarCargoDto(@NotNull UUID FuncionarioId,
                                @NotNull String Cargo) { }
