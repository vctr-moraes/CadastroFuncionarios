package com.webservice.cadastro_funcionarios.interfaces;

import com.webservice.cadastro_funcionarios.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> { }
