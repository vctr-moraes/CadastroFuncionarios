package com.webservice.cadastro_funcionarios.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public class EnderecoDto {

    @Id
    public UUID Id;

    @NotBlank(message = "O logradouro deve ser informado")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres")
    public String Logradouro;

    @NotBlank(message = "O bairro deve ser informado")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre 3 e 100 caracteres")
    public String Bairro;

    @NotBlank(message = "O complemento deve ser informado")
    @Size(min = 3, max = 100, message = "O complemento deve ter entre 3 e 100 caracteres")
    public String Complemento;

    @NotBlank(message = "O cidade deve ser informado")
    @Size(min = 3, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    public String Cidade;

    @NotBlank(message = "O estado deve ser informado")
    @Size(min = 2, max = 50, message = "O estado deve ter entre 2 e 50 caracteres")
    public String Estado;

    @NotBlank(message = "O país deve ser informado")
    @Size(min = 2, max = 50, message = "O país deve ter entre 2 e 50 caracteres")
    public String Pais;

    @NotBlank(message = "O cep deve ser informado")
    @Size(min = 8, max = 10, message = "O cep deve ter entre 8 e 10 caracteres")
    public String Cep;

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
}