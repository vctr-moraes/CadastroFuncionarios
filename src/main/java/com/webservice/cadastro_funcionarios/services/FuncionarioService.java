package com.webservice.cadastro_funcionarios.services;

import com.webservice.cadastro_funcionarios.models.Funcionario;

public interface FuncionarioService {

    void CadastrarFuncionario(Funcionario funcionario);
    void AtualizarFuncionario(Funcionario funcionario);
    void ExcluirFuncionario(Funcionario funcionario);
}
