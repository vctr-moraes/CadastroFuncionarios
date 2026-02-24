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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioRepository funcionarioRepository) {
        this.funcionarioService = funcionarioService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping("/funcionarios/cadastrar")
    public ResponseEntity<FuncionarioDto> CadastrarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {

        try {
            var funcionario = new Funcionario();

            BeanUtils.copyProperties(funcionarioDto, funcionario);

            funcionarioService.CadastrarFuncionario(funcionario);

            return ResponseEntity.status(HttpStatus.CREATED).body(null);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/funcionarios/atualizar")
    public ResponseEntity<FuncionarioDto> AtualizarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {

        try {
            Funcionario funcionarioExistente = funcionarioRepository.findById(funcionarioDto.Id).orElse(null);

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
    public ResponseEntity<List<FuncionarioDto>> ListarFuncionarios() {

        try {
            var funcionarios = funcionarioRepository.findAll();

            var funcionariosDto = funcionarios.stream().map(funcionario -> {
                var funcionarioDto = new FuncionarioDto();
                BeanUtils.copyProperties(funcionario, funcionarioDto);
                return funcionarioDto;
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(funcionariosDto);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/funcionarios/excluir")
    public ResponseEntity<Funcionario> ExcluirFuncionario(UUID funcionarioId) {

        try {
            var funcionarioExistente = funcionarioRepository.findById(funcionarioId).orElse(null);

            if (funcionarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            funcionarioService.ExcluirFuncionario(funcionarioExistente);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/funcionarios/buscar-por-id/{funcionarioId}")
    public ResponseEntity<FuncionarioDto> ObterFuncionarioPorId(@PathVariable UUID funcionarioId) {

        try {
            var funcionario = funcionarioRepository.findById(funcionarioId).orElse(null);

            if (funcionario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            var funcionarioDto = new FuncionarioDto();
            BeanUtils.copyProperties(funcionario, funcionarioDto);

            return ResponseEntity.status(HttpStatus.OK).body(funcionarioDto);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
