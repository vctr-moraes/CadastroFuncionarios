package com.webservice.cadastro_funcionarios.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Funcionarios")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Nome;
    private String Email;
    private String Documento;
    private LocalDate DataNascimento;
    private LocalDate DataCadastro = LocalDate.now();
    private Boolean Status;
    private Float Salario;

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public LocalDate getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        DataCadastro = dataCadastro;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public Float getSalario() {
        return Salario;
    }

    public void setSalario(Float salario) {
        Salario = salario;
    }
}
