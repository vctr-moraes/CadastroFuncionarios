package com.webservice.cadastro_funcionarios.services;

import com.webservice.cadastro_funcionarios.interfaces.FuncionarioRepository;
import com.webservice.cadastro_funcionarios.models.Funcionario;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public void CadastrarFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }
}
