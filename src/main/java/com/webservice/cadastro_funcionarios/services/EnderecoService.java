package com.webservice.cadastro_funcionarios.services;

import com.webservice.cadastro_funcionarios.interfaces.EnderecoRepository;
import com.webservice.cadastro_funcionarios.models.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public void CadastrarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
}
