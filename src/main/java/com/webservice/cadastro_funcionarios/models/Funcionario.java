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
    @Column(name = "id")
    private UUID Id;

    @Column(name = "nome")
    private String Nome;

    @Column(name = "email")
    private String Email;

    @Column(name = "documento")
    private String Documento;

    @Column(name = "data_nascimento")
    private LocalDate DataNascimento;

    @Column(name = "data_cadastro")
    private LocalDate DataCadastro = LocalDate.now();

    @Column(name = "status")
    private Boolean Status;

    @Column(name = "salario")
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
