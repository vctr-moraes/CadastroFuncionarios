package com.webservice.cadastro_funcionarios.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ReajusteSalarioDto(@NotNull UUID FuncionarioId,
                                 @NotNull @DecimalMin(value = "1.0", message = "O ajuste deve ser superior a 1%") Float PercentualAjuste) { }
