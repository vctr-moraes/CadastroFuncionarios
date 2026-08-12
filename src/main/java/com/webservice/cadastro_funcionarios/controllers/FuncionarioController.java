package com.webservice.cadastro_funcionarios.controllers;

import com.webservice.cadastro_funcionarios.dtos.FuncionarioDto;
import com.webservice.cadastro_funcionarios.dtos.ModificarCargoDto;
import com.webservice.cadastro_funcionarios.dtos.ReajusteSalarioDto;
import com.webservice.cadastro_funcionarios.exceptions.NotFoundException;
import com.webservice.cadastro_funcionarios.interfaces.FuncionarioRepository;
import com.webservice.cadastro_funcionarios.models.Cargo;
import com.webservice.cadastro_funcionarios.models.Endereco;
import com.webservice.cadastro_funcionarios.models.Funcionario;
import com.webservice.cadastro_funcionarios.services.EnderecoService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    final FuncionarioRepository funcionarioRepository;
    final EnderecoService enderecoService;

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    public FuncionarioController(FuncionarioService funcionarioService,
                                 FuncionarioRepository funcionarioRepository,
                                 EnderecoService enderecoService) {
        this.funcionarioService = funcionarioService;
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> ListarFuncionarios() {
        try {
            var funcionarios = funcionarioRepository.findAll();

            var funcionariosDto = funcionarios.stream().map(funcionario -> {
                var funcionarioDto = new FuncionarioDto();
                BeanUtils.copyProperties(funcionario, funcionarioDto);
                funcionarioDto.Links.add(linkTo(methodOn(FuncionarioController.class).ObterFuncionarioPorId(funcionario.getId())).withSelfRel().getHref());
                funcionarioDto.setCargo(funcionario.getCargo().name());
                return funcionarioDto;
            }).collect(Collectors.toList());

            logger.info("Funcionários localizados com sucesso.");

            return ResponseEntity.status(HttpStatus.OK).body(funcionariosDto);

        } catch (Exception e) {
            logger.error("Erro ao consultar funcionários: ", e);
            throw e;
        }
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<Object> ObterFuncionarioPorId(@PathVariable UUID funcionarioId) {
        try {
            var funcionario = funcionarioRepository.findById(funcionarioId).orElse(null);

            if (funcionario == null) {
                logger.info("Funcionário não localizado.");
                throw new NotFoundException("Funcionário não localizado.");
            }

            var funcionarioDto = new FuncionarioDto();

            BeanUtils.copyProperties(funcionario, funcionarioDto);
            funcionarioDto.Links.add(linkTo(methodOn(FuncionarioController.class).ListarFuncionarios()).withRel("funcionarios").getHref());
            funcionarioDto.setCargo(funcionario.getCargo().name());

            logger.info("Funcionário localizado com sucesso.");

            return ResponseEntity.status(HttpStatus.OK).body(funcionarioDto);

        } catch (Exception e) {
            logger.error("Erro ao consultar funcionário: ", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Object> CadastrarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {
        try {
            var funcionario = new Funcionario();
            BeanUtils.copyProperties(funcionarioDto, funcionario);
            funcionario.setCargo(Cargo.valueOf(funcionarioDto.getCargo()));

            var endereco = new Endereco();
            BeanUtils.copyProperties(funcionarioDto.Endereco, endereco);
            endereco.setFuncionario(funcionario);

            funcionarioService.CadastrarFuncionario(funcionario);
            enderecoService.CadastrarEndereco(endereco);

            logger.info("Funcionário cadastrado com sucesso.");

            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDto);

        } catch (Exception e) {
            logger.error("Erro ao cadastrar funcionário: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<Object> AtualizarFuncionario(@PathVariable UUID funcionarioId,
                                                       @RequestBody @Valid FuncionarioDto funcionarioDto) {

        try {
            var funcionarioExistente = funcionarioRepository.findById(funcionarioId).orElse(null);

            if (funcionarioExistente == null) {
                logger.info("Funcionário não localizado.");
                throw new NotFoundException("Funcionário não localizado.");
            }

            BeanUtils.copyProperties(funcionarioDto, funcionarioExistente);
            funcionarioExistente.setCargo(Cargo.valueOf(funcionarioDto.getCargo()));

            funcionarioService.AtualizarFuncionario(funcionarioExistente);

            logger.info("Funcionário atualizado com sucesso.");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(funcionarioExistente);

        } catch (Exception e) {
            logger.error("Erro ao atualizar funcionário: ", e);
            throw e;
        }
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Object> ExcluirFuncionario(@PathVariable UUID funcionarioId) {
        try {
            var funcionarioExistente = funcionarioRepository.findById(funcionarioId).orElse(null);

            if (funcionarioExistente == null) {
                logger.info("Funcionário não localizado.");
                throw new NotFoundException("Funcionário não localizado.");
            }

            funcionarioService.ExcluirFuncionario(funcionarioExistente);

            logger.info("Funcionário excluído com sucesso.");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Funcionário excluído com sucesso.");

        } catch (Exception e) {
            logger.error("Erro ao excluir funcionário: ", e);
            throw e;
        }
    }

    @PostMapping("/reajustar-salario")
    public ResponseEntity<Object> ReajustarSalario(@RequestBody @Valid ReajusteSalarioDto reajusteSalarioDto) {
        try {
            var funcionario = funcionarioRepository.findById(reajusteSalarioDto.FuncionarioId()).orElse(null);

            if (funcionario == null) {
                logger.info("Funcionário não localizado.");
                throw new NotFoundException("Funcionário não localizado.");
            }

            funcionario.reajustarSalario(reajusteSalarioDto.PercentualAjuste());

            funcionarioService.AtualizarFuncionario(funcionario);

            logger.info("Salário do funcionário reajustado com sucesso.");

            return ResponseEntity.status(HttpStatus.OK).body("Salário do funcionário reajustado com sucesso.");

        } catch (Exception e) {
            logger.error("Erro ao reajustar salário do funcionário: ", e);
            throw e;
        }
    }

    @PostMapping("/modificar-cargo")
    public ResponseEntity<Object> ModificarCargo(@RequestBody @Valid ModificarCargoDto modificarCargoDto) {
        try {
            var funcionario = funcionarioRepository.findById(modificarCargoDto.FuncionarioId()).orElse(null);

            if (funcionario == null) {
                logger.info("Funcionário não localizado.");
                throw new NotFoundException("Funcionário não localizado.");
            }

            if (funcionario.getCargo().name().equals(modificarCargoDto.Cargo())) {
                logger.info("O cargo informado já é o atual do funcionário.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cargo informado já é o atual do funcionário.");
            }

            funcionario.modificarCargo(Cargo.valueOf(modificarCargoDto.Cargo()));

            funcionarioService.AtualizarFuncionario(funcionario);

            logger.info("Cargo do funcionário modificado com sucesso.");

            return ResponseEntity.status(HttpStatus.OK).body("Cargo do funcionário modificado com sucesso.");

        } catch (Exception e) {
            logger.error("Erro ao modificar cargo do funcionário: ", e);
            throw e;
        }
    }
}
