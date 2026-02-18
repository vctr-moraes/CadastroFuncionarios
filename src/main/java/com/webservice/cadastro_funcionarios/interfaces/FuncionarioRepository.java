package com.webservice.cadastro_funcionarios.interfaces;

import com.webservice.cadastro_funcionarios.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> { }
