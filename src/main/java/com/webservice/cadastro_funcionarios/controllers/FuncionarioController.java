package com.webservice.cadastro_funcionarios.controllers;

import com.webservice.cadastro_funcionarios.dtos.FuncionarioDto;
import com.webservice.cadastro_funcionarios.interfaces.FuncionarioRepository;
import com.webservice.cadastro_funcionarios.models.Funcionario;
import com.webservice.cadastro_funcionarios.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioRepository funcionarioRepository) {
        this.funcionarioService = funcionarioService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping("/funcionarios/cadastrar")
    public ResponseEntity<Funcionario> CadastrarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {

        try {
            var funcionario = new Funcionario();

            BeanUtils.copyProperties(funcionarioDto, funcionario);

            funcionarioService.CadastrarFuncionario(funcionario);

            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/funcionarios/atualizar")
    public ResponseEntity<Funcionario> AtualizarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {

        try {
            Funcionario funcionarioExistente = funcionarioRepository.findById(funcionarioDto.id()).get();

            if (funcionarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            BeanUtils.copyProperties(funcionarioDto, funcionarioExistente);

            funcionarioService.AtualizarFuncionario(funcionarioExistente);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/funcionarios/listar-todos")
    public ResponseEntity<List<Funcionario>> ListarFuncionarios() {

        try {
            var funcionarios = funcionarioRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
