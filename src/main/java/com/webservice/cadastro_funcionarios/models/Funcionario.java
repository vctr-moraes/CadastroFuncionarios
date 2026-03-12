package com.webservice.cadastro_funcionarios.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Column(name = "nome", nullable = false, length = 100)
    private String Nome;

    @Column(name = "email", nullable = false, length = 100)
    private String Email;

    @Column(name = "documento", nullable = false, length = 20)
    private String Documento;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate DataNascimento;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate DataCadastro = LocalDate.now();

    @Column(name = "status", nullable = false)
    private Boolean Status;

    @Column(name = "salario", nullable = false)
    private Float Salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false, length = 20)
    private Cargo Cargo;

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

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo cargo) {
        Cargo = cargo;
    }

    public void reajustarSalario(Float percentualAjuste) {
        this.Salario += this.Salario * (percentualAjuste / 100);
    }
}
