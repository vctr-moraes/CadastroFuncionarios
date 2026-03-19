package com.webservice.cadastro_funcionarios.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Entity
@Component
@Table(name = "Enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String Logradouro;

    @Column(name = "bairro", nullable = false, length = 100)
    private String Bairro;

    @Column(name = "complemento", length = 100)
    private String Complemento;

    @Column(name = "cidade", nullable = false, length = 100)
    private String Cidade;

    @Column(name = "estado", nullable = false, length = 50)
    private String Estado;

    @Column(name = "pais", nullable = false, length = 50)
    private String Pais;

    @Column(name = "cep", nullable = false, length = 10)
    private String Cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "funcionario_id")
    public Funcionario Funcionario;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public Funcionario getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        Funcionario = funcionario;
    }
}
