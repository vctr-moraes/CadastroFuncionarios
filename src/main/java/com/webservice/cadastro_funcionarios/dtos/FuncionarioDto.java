package com.webservice.cadastro_funcionarios.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

public class FuncionarioDto {

    @Id
    public UUID Id;

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    public String Nome;

    @NotBlank(message = "O email deve ser informado")
    @Size(min = 5, max = 100, message = "O email deve ter entre 5 e 100 caracteres")
    @Email(message = "O email deve ser válido")
    public String Email;

    @NotBlank(message = "O documento deve ser informado")
    @Size(min = 5, max = 20, message = "O documento deve ter entre 5 e 20 caracteres")
    public String Documento;

    @NotNull(message = "A data de nascimento deve ser informada")
    @Past(message = "A data de nascimento deve ser anterior a data atual")
    public LocalDate DataNascimento;

    @NotNull(message = "O status deve ser informado")
    public Boolean Status;

    @NotNull(message = "O salário deve ser informado")
    @DecimalMin(value = "1.0", message = "O salário deve ser maior ou igual a 1.0")
    public Float Salario;

    @NotNull(message = "O cargo deve ser informado")
    public String Cargo;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Float getSalario() {
        return Salario;
    }

    public void setSalario(Float salario) {
        Salario = salario;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        this.Cargo = cargo;
    }
}
