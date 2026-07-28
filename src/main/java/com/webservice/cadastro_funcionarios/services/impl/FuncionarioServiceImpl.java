package com.webservice.cadastro_funcionarios.services.impl;

import org.springframework.stereotype.Service;

import com.webservice.cadastro_funcionarios.interfaces.FuncionarioRepository;
import com.webservice.cadastro_funcionarios.models.Funcionario;
import com.webservice.cadastro_funcionarios.services.FuncionarioService;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void CadastrarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Override
    public void AtualizarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

   @Transactional
    @Override
    public void ExcluirFuncionario(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }
}
