package com.webservice.cadastro_funcionarios.services.impl;

import com.webservice.cadastro_funcionarios.interfaces.EnderecoRepository;
import com.webservice.cadastro_funcionarios.models.Endereco;
import com.webservice.cadastro_funcionarios.services.EnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void CadastrarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
}
